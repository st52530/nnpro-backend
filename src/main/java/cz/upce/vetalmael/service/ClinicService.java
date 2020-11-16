package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.Clinic;
import cz.upce.vetalmael.model.ClinicMedicine;
import cz.upce.vetalmael.model.dto.ClinicDto;

import java.util.List;

public interface ClinicService {

    Clinic addClinic(ClinicDto clinicDto);
    Clinic editClinic(ClinicDto clinicDto, int idClinic);
    void removeClinic(int idClinic);
    Clinic getClinic(int idClinic);
    List<Clinic> getClinics();
}
