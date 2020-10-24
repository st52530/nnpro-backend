package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.Medicine;
import cz.upce.vetalmael.model.dto.MedicineDto;

public interface MedicineService {

    Medicine addMedicine(MedicineDto medicineDto);
    Medicine editMedicine(MedicineDto medicineDto);
    void removeMedicine(int idMedicine);
}
