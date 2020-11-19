package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.ClinicConsumable;
import cz.upce.vetalmael.model.ClinicMedicine;
import cz.upce.vetalmael.model.dto.ClinicMedicineDto;

import java.util.List;

public interface ClinicMedicineService {

    ClinicMedicine addClinicMedicine(ClinicMedicineDto clinicMedicineDto, int idClinic, int idMedicine) throws Exception;
    ClinicMedicine editClinicMedicine(ClinicMedicineDto clinicMedicineDto, int idClinicMedicine);
    void removeClinicMedicine(int idClinicMedicine);
    ClinicMedicine getClinicMedicine(int idClinic, int idMedicine);
    List<ClinicMedicine> getClinicMedicines(int idClinic);
    void removeClinicMedicine();
}
