package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.Clinic;
import cz.upce.vetalmael.model.dto.ClinicDto;

public interface ClinicService {

    Clinic addClinic(ClinicDto clinicDto);
    void removeClinic(int idClinic);
}
