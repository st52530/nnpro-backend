package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Clinic;
import cz.upce.vetalmael.model.ClinicMedicine;
import cz.upce.vetalmael.model.Medicine;
import cz.upce.vetalmael.model.dto.ClinicMedicineDto;
import cz.upce.vetalmael.repository.ClinicMedicineRepository;
import cz.upce.vetalmael.service.ClinicMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "clinicMedicineService")
public class ClinicMedicineServiceImpl implements ClinicMedicineService {

    @Autowired
    private ClinicMedicineRepository clinicMedicineRepository;

    @Override
    public ClinicMedicine addClinicMedicine(ClinicMedicineDto clinicMedicineDto, int idClinic, int idMedicine) {
        ClinicMedicine clinicMedicine = new ClinicMedicine();
        clinicMedicine.setQuantityInStock(clinicMedicineDto.getQuantityInStock());
        Clinic clinic = new Clinic();
        clinic.setIdClinic(idClinic);
        Medicine medicine = new Medicine();
        medicine.setIdMedicine(idMedicine);
        clinicMedicine.setClinic(clinic);
        clinicMedicine.setMedicine(medicine);
        return clinicMedicineRepository.save(clinicMedicine);
    }

    @Override
    public ClinicMedicine addQuantityInStock(ClinicMedicineDto clinicMedicineDto, int idClinicMedicine) {
        ClinicMedicine clinicMedicine = new ClinicMedicine();
        clinicMedicine.setIdClinicMedicine(idClinicMedicine);
        int quantityInStock = clinicMedicineRepository.getOne(idClinicMedicine).getQuantityInStock();
        clinicMedicine.setQuantityInStock(clinicMedicineDto.getQuantityInStock()+quantityInStock);
        return clinicMedicineRepository.save(clinicMedicine);
    }

    @Override
    public void removeClinicMedicine(int idClinicMedicine) {
        clinicMedicineRepository.deleteById(idClinicMedicine);
    }
}
