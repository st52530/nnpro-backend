package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Clinic;
import cz.upce.vetalmael.model.dto.ClinicDto;
import cz.upce.vetalmael.repository.ClinicRepository;
import cz.upce.vetalmael.service.ClinicService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "clinicService")
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ClinicRepository clinicRepository;

    @Override
    public Clinic addClinic(ClinicDto clinicDto) {
        Clinic clinic = modelMapper.map(clinicDto, Clinic.class);
        return clinicRepository.save(clinic);

    }

    @Override
    public void removeClinic(int idClinic) {
        clinicRepository.deleteById(idClinic);
    }

    @Override
    public Clinic getClinic(int idClinic) {
        return clinicRepository.getOne(idClinic);
    }

    @Override
    public List<Clinic> getClinics() {
        return clinicRepository.findAll();
    }
}
