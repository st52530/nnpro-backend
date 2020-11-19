package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Clinic;
import cz.upce.vetalmael.model.ClinicMedicine;
import cz.upce.vetalmael.model.Medicine;
import cz.upce.vetalmael.model.dto.ClinicMedicineDto;
import cz.upce.vetalmael.repository.ClinicMedicineRepository;
import cz.upce.vetalmael.service.ClinicMedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service(value = "clinicMedicineService")
@Transactional
public class ClinicMedicineServiceImpl implements ClinicMedicineService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ClinicMedicineRepository clinicMedicineRepository;

    @Override
    public ClinicMedicine addClinicMedicine(ClinicMedicineDto clinicMedicineDto, int idClinic, int idMedicine) throws Exception{
        ClinicMedicine clinicMedicine = new ClinicMedicine();
        if (clinicMedicineRepository.countAllByClinic_IdClinicAndMedicine_IdMedicine(idClinic, idMedicine) == 0) {
            clinicMedicine.setQuantityInStock(clinicMedicineDto.getQuantityInStock());
            Clinic clinic = entityManager.getReference(Clinic.class, idClinic);
            clinic.setIdClinic(idClinic);
            Medicine medicine = entityManager.getReference(Medicine.class, idMedicine);
            medicine.setIdMedicine(idMedicine);
            clinicMedicine.setClinic(clinic);
            clinicMedicine.setMedicine(medicine);
            return clinicMedicineRepository.save(clinicMedicine);
        }
        throw new Exception("Can't add same medicine to clinic");
    }

    @Override
    public ClinicMedicine editClinicMedicine(ClinicMedicineDto clinicMedicineDto, int idClinicMedicine) {
        ClinicMedicine one = clinicMedicineRepository.getOne(idClinicMedicine);
        one.setQuantityInStock(clinicMedicineDto.getQuantityInStock());
        return clinicMedicineRepository.save(one);
    }

    @Override
    public void removeClinicMedicine(int idClinicMedicine) {
        clinicMedicineRepository.deleteById(idClinicMedicine);
    }

    @Override
    public ClinicMedicine getClinicMedicine(int idClinic, int idMedicine) {
        return clinicMedicineRepository.findByClinic_IdClinicAndMedicine_IdMedicine(idClinic, idMedicine);
    }

    @Override
    public List<ClinicMedicine> getClinicMedicines(int idClinic) {
        return clinicMedicineRepository.findAllByClinic_IdClinic(idClinic);
    }

    @Override
    public void removeClinicMedicine() {
        clinicMedicineRepository.deleteAll();
    }
}
