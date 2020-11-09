package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.Consumable;
import cz.upce.vetalmael.model.Diagnosis;
import cz.upce.vetalmael.model.dto.DiagnosisDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DiagnosisService {

    Diagnosis addDiagnosis(DiagnosisDto diagnosisDto);
    List<Diagnosis> importDiagnosis(MultipartFile file) throws IOException;
    Diagnosis editDiagnosis(DiagnosisDto diagnosisDto, int idDiagnosis);
    void removeDiagnosis(int idDiagnosis);
    List<Diagnosis> getAnimalDiagnosis(int idAnimal);

}
