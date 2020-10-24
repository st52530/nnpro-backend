package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Diagnosis;
import cz.upce.vetalmael.model.dto.DiagnosisDto;
import cz.upce.vetalmael.repository.DiagnosisRepository;
import cz.upce.vetalmael.service.DiagnosisService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "diagnosisService")
public class DiagnosisServiceImpl implements DiagnosisService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DiagnosisRepository diagnosisRepository;

    @Override
    public Diagnosis addDiagnosis(DiagnosisDto diagnosisDto) {
        Diagnosis diagnosis = modelMapper.map(diagnosisDto, Diagnosis.class);
        return diagnosisRepository.save(diagnosis);
    }

    @Override
    public Diagnosis editDiagnosis(DiagnosisDto diagnosisDto) {
        Diagnosis diagnosis = modelMapper.map(diagnosisDto, Diagnosis.class);
        return diagnosisRepository.save(diagnosis);
    }

    @Override
    public void removeDiagnosis(int idDiagnosis) {
        diagnosisRepository.deleteById(idDiagnosis);
    }
}
