package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.ClinicMedicine;
import cz.upce.vetalmael.model.dto.ClinicMedicineDto;

public interface ClinicMedicineService {

    ClinicMedicine addClinicMedicine(ClinicMedicineDto clinicMedicineDto);
    ClinicMedicine addQuantityInStock(ClinicMedicineDto clinicMedicineDto);
    void removeClinicMedicine(int idClinicMedicine);
}
