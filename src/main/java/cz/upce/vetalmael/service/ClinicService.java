package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.dto.ClinicDto;

public interface ClinicService {

    void addClinic(ClinicDto clinicDto);
    void removeClinic(int idClinic);
}
