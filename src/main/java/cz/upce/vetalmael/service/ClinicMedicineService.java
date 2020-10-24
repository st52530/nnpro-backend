package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.ClinicMedicine;
import cz.upce.vetalmael.model.dto.ClinicMedicineDto;

public interface ClinicMedicineService {

    ClinicMedicine addClinicMedicine(ClinicMedicineDto clinicMedicineDto, int idClinic, int idMedicine);
    ClinicMedicine addQuantityInStock(ClinicMedicineDto clinicMedicineDto, int idClinicMedicine);
    void removeClinicMedicine(int idClinicMedicine);
}
