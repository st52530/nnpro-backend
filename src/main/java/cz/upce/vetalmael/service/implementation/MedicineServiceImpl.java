package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Medicine;
import cz.upce.vetalmael.model.dto.MedicineDto;
import cz.upce.vetalmael.repository.MedicineRepository;
import cz.upce.vetalmael.service.MedicineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "medicineService")
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public Medicine addMedicine(MedicineDto medicineDto) {
        Medicine medicine = modelMapper.map(medicineDto, Medicine.class);
        return medicineRepository.save(medicine);
    }

    @Override
    public Medicine editMedicine(MedicineDto medicineDto) {
        Medicine medicine = modelMapper.map(medicineDto, Medicine.class);
        return medicineRepository.save(medicine);
    }

    @Override
    public void removeMedicine(int idMedicine) {
        medicineRepository.deleteById(idMedicine);
    }
}
