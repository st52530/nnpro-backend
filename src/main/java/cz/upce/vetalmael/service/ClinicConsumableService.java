package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.ClinicConsumable;
import cz.upce.vetalmael.model.dto.ClinicConsumableDto;

import java.util.List;

public interface ClinicConsumableService {

    ClinicConsumable addClinicConsumable(ClinicConsumableDto clinicConsumableDto, int idClinic, int idConsumable);
    ClinicConsumable addQuantityInStock(ClinicConsumableDto clinicConsumableDto, int idClinicConsumable);
    void removeClinicConsumable(int idClinicConsumable);
    ClinicConsumable getClinicConsumable(int idClinic, int idConsumable);
    List<ClinicConsumable> getClinicConsumables(int idClinic);
}
