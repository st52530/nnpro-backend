package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Clinic;
import cz.upce.vetalmael.model.ClinicMedicine;
import cz.upce.vetalmael.model.Medicine;
import cz.upce.vetalmael.model.dto.ClinicMedicineDto;
import cz.upce.vetalmael.repository.ClinicMedicineRepository;
import cz.upce.vetalmael.service.ClinicMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "clinicMedicine")
public class ClinicMedicineImpl implements ClinicMedicineService {

    @Autowired
    private ClinicMedicineRepository clinicMedicineRepository;

    @Override
    public ClinicMedicine addClinicMedicine(ClinicMedicineDto clinicMedicineDto) {
        ClinicMedicine clinicMedicine = new ClinicMedicine();
        clinicMedicine.setQuantityInStock(clinicMedicineDto.getQuantityInStock());
        Clinic clinic = new Clinic();
        clinic.setIdClinic(clinicMedicineDto.getClinicDto().getIdClinic());
        Medicine medicine = new Medicine();
        medicine.setIdMedicine(clinicMedicineDto.getMedicineDto().getIdMedicine());
        clinicMedicine.setClinic(clinic);
        clinicMedicine.setMedicine(medicine);
        return clinicMedicineRepository.save(clinicMedicine);
    }

    @Override
    public ClinicMedicine addQuantityInStock(ClinicMedicineDto clinicMedicineDto) {
        ClinicMedicine clinicMedicine = new ClinicMedicine();
        clinicMedicine.setIdClinicMedicine(clinicMedicineDto.getIdClinicMedicine());
        clinicMedicine.setQuantityInStock(0);
        return clinicMedicineRepository.save(clinicMedicine);
    }

    @Override
    public void removeClinicMedicine(int idClinicMedicine) {
        clinicMedicineRepository.deleteById(idClinicMedicine);
    }
}
