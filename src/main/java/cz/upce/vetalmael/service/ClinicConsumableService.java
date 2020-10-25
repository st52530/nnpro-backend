package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.ClinicConsumable;
import cz.upce.vetalmael.model.dto.ClinicConsumableDto;

public interface ClinicConsumableService {

    ClinicConsumable addClinicConsumable(ClinicConsumableDto clinicConsumableDto, int idClinic, int idConsumable);
    ClinicConsumable addQuantityInStock(ClinicConsumableDto clinicConsumableDto, int idClinicConsumable);
    void removeClinicConsumable(int idClinicConsumable);
}
