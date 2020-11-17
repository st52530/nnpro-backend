package cz.upce.vetalmael.service.implementation;

import com.itextpdf.text.*;
import cz.upce.vetalmael.model.*;
import cz.upce.vetalmael.repository.ReportRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import cz.upce.vetalmael.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Stream;

@Service(value = "exportService")
public class ExportServiceImpl implements ExportService {

    @Autowired
    ReportRepository reportRepository;

    public ByteArrayInputStream exportToPdf(int idReport) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();

        Report report = reportRepository.findById(idReport).orElse(null);

        if (null == report)
            return null;

        try {
            PdfWriter.getInstance(document, out);

            document.open();

            PdfPTable tableDescription = new PdfPTable(2);
            addTableHeader(tableDescription, new String[]{"Popis", "Hodnota"});

            addRows(tableDescription, "Potíže", report.getTextDescription());

            addRows(tableDescription, "Popis diagnozy", report.getTextDiagnosis());

            addRows(tableDescription, "Doporučení", report.getTextRecommendation());

            addRows(tableDescription, "Datum návštěvy", report.getDate().toString());

            addRows(tableDescription, "Jméno zvířete", report.getAnimal().getName());

            addRows(tableDescription, "Jméno veterináře", report.getVeterinary().getFullName());

            addRows(tableDescription, "Jméno majitele", report.getAnimal().getOwner().getFullName());

            addRows(tableDescription, "Název diagnozy", report.getDiagnosis().getName());

            document.add(tableDescription);

            PdfPTable pricesTable = new PdfPTable(2);
            addTableHeader(pricesTable, new String[]{"Název", "Cena za jednotku"});

            addRowsConsumable(pricesTable, report.getConsumables());

            addRowsOperation(pricesTable, report.getOperation());

            addRowsMedicines(pricesTable, report.getMedicines());

            document.add(pricesTable);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    private static void addTableHeader(PdfPTable table, String[] headers) {
        Stream.of(headers)
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }

    private static void addRowsConsumable(PdfPTable table, Set<Consumable> consumables) {
        for (Consumable consumable : consumables) {
            table.addCell(consumable.getName());
            table.addCell(String.valueOf(consumable.getPrice()));
        }

    }

    private static void addRowsMedicines(PdfPTable table, Set<Medicine> medicines) {
        for (Medicine medicine : medicines) {
            table.addCell(medicine.getName());
            table.addCell("0");
        }

    }

    private static void addRowsOperation(PdfPTable table, Operation operation) {
        table.addCell(operation.getName());
        table.addCell(String.valueOf(operation.getPrice()));
    }

    private static void addRows(PdfPTable table, String description, String value) {
        table.addCell(description);
        table.addCell(value);
    }
}
