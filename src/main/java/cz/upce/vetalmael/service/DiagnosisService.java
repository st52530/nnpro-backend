package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.Diagnosis;
import cz.upce.vetalmael.model.dto.DiagnosisDto;

public interface DiagnosisService {

    Diagnosis addDiagnosis(DiagnosisDto diagnosisDto);
    Diagnosis editDiagnosis(DiagnosisDto diagnosisDto);
    void removeDiagnosis(int idDiagnosis);

}
