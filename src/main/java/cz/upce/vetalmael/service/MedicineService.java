package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.Diagnosis;
import cz.upce.vetalmael.model.Medicine;
import cz.upce.vetalmael.model.dto.MedicineDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MedicineService {

    Medicine addMedicine(MedicineDto medicineDto);
    List<Medicine> importMedicine(MultipartFile file) throws IOException;
    Medicine editMedicine(MedicineDto medicineDto, int idMedicine);
    void removeMedicine(int idMedicine);
}
