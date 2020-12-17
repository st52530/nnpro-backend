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

        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN, "Cp1250", true);

        Report report = reportRepository.findById(idReport).orElse(null);

        if (null == report)
            return null;

        try {
            PdfWriter.getInstance(document, out);

            document.open();

            PdfPTable tableDescription = new PdfPTable(2);
            addTableHeader(tableDescription, new String[]{"Popis", "Hodnota"}, font);

            if (report.getTextDescription() != null)
                addRows(tableDescription, "Potíže", report.getTextDescription(), font);

            if (report.getTextDiagnosis() != null)
                addRows(tableDescription, "Popis diagnozy", report.getTextDiagnosis(), font);

            if (report.getTextRecommendation() != null)
                addRows(tableDescription, "Doporučení", report.getTextRecommendation(), font);

            if (report.getDate() != null)
                addRows(tableDescription, "Datum návštěvy", report.getDate().toString(), font);

            if (report.getAnimal() != null)
                addRows(tableDescription, "Jméno zvířete", report.getAnimal().getName(), font);

            if (report.getVeterinary() != null)
                addRows(tableDescription, "Jméno veterináře", report.getVeterinary().getFullName(), font);

            if (report.getAnimal() != null && report.getAnimal().getOwner() != null)
                addRows(tableDescription, "Jméno majitele", report.getAnimal().getOwner().getFullName(), font);

            if (report.getDiagnosis() != null)
                addRows(tableDescription, "Název diagnozy", report.getDiagnosis().getName(), font);

            document.add(tableDescription);

            PdfPTable pricesTable = new PdfPTable(2);
            double summaryPrice = 0;
            addTableHeader(pricesTable, new String[]{"Název", "Cena za jednotku"}, font);

            if (report.getConsumables() != null && !report.getConsumables().isEmpty())
                summaryPrice += addRowsConsumable(pricesTable, report.getConsumables(), font);

            if (report.getOperation() != null)
                summaryPrice += addRowsOperation(pricesTable, report.getOperation(), font);

            if (report.getMedicines() != null && !report.getMedicines().isEmpty())
                addRowsMedicines(pricesTable, report.getMedicines(), font);

            addTableHeader(pricesTable, new String[]{"Celková cena", summaryPrice + " CZK"}, font);

            document.add(pricesTable);

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ByteArrayInputStream(out.toByteArray());
    }

    private static void addTableHeader(PdfPTable table, String[] headers, Font font) {
        Stream.of(headers)
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle, font));
                    table.addCell(header);
                });
    }

    private static double addRowsConsumable(PdfPTable table, Set<Consumable> consumables, Font font) {
        double summaryPrice = 0;
        for (Consumable consumable : consumables) {
            table.addCell(new Phrase(consumable.getName(), font));
            table.addCell(new Phrase(String.valueOf(consumable.getPrice()), font));
            summaryPrice = summaryPrice + consumable.getPrice();
        }
        return summaryPrice;
    }

    private static void addRowsMedicines(PdfPTable table, Set<Medicine> medicines, Font font) {
        for (Medicine medicine : medicines) {
            table.addCell(new Phrase(medicine.getName(), font));
            table.addCell(new Phrase("0", font));
        }
    }

    private static double addRowsOperation(PdfPTable table, Operation operation, Font font) {
        table.addCell(new Phrase(operation.getName(), font));
        table.addCell(new Phrase(String.valueOf(operation.getPrice()), font));
        return operation.getPrice();
    }

    private static void addRows(PdfPTable table, String description, String value, Font font) {
        table.addCell(new Phrase(description, font));
        table.addCell(new Phrase(value, font));
    }
}
