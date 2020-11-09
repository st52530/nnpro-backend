package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Diagnosis;
import cz.upce.vetalmael.model.Report;
import cz.upce.vetalmael.model.dto.DiagnosisDto;
import cz.upce.vetalmael.repository.AnimalRepository;
import cz.upce.vetalmael.repository.DiagnosisRepository;
import cz.upce.vetalmael.repository.ReportRepository;
import cz.upce.vetalmael.service.DiagnosisService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service(value = "diagnosisService")
public class DiagnosisServiceImpl implements DiagnosisService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Diagnosis addDiagnosis(DiagnosisDto diagnosisDto) {
        Diagnosis diagnosis = modelMapper.map(diagnosisDto, Diagnosis.class);
        return diagnosisRepository.save(diagnosis);
    }

    @Override
    public List<Diagnosis> importDiagnosis(MultipartFile file) throws IOException {
        List<Diagnosis> list = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
        XSSFSheet worksheet = workbook.getSheetAt(0);
        for (int index = 1; index < worksheet.getPhysicalNumberOfRows(); index++) {
            if (index > 1) {
                Diagnosis diagnosis = new Diagnosis();

                XSSFRow row = worksheet.getRow(index);
                diagnosis.setTargetAnimals(row.getCell(0).getStringCellValue());
                diagnosis.setType(row.getCell(1).getStringCellValue());
                diagnosis.setName(row.getCell(2).getStringCellValue());
                diagnosis.setSymptoms(row.getCell(3).getStringCellValue());
                diagnosis.setIncubationPeriod(row.getCell(4).getStringCellValue());
                diagnosis.setTreatment(row.getCell(5).getStringCellValue());
                diagnosis.setPrevention(row.getCell(6).getStringCellValue());

                list.add(diagnosis);
            }
        }
        return diagnosisRepository.saveAll(list);
    }

    @Override
    public Diagnosis editDiagnosis(DiagnosisDto diagnosisDto, int idDiagnosis) {
        Diagnosis diagnosis = modelMapper.map(diagnosisDto, Diagnosis.class);
        diagnosis.setIdDiagnosis(idDiagnosis);
        return diagnosisRepository.save(diagnosis);
    }

    @Override
    public void removeDiagnosis(int idDiagnosis) {
        diagnosisRepository.deleteById(idDiagnosis);
    }

    @Override
    public List<Diagnosis> getAnimalDiagnosis(int idAnimal) {
        return reportRepository.findAllByAnimal_IdAnimal(idAnimal).stream().map(Report::getDiagnosis).collect(Collectors.toList());
    }
}
