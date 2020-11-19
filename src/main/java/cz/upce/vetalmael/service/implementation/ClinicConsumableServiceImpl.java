package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.*;
import cz.upce.vetalmael.model.dto.ClinicConsumableDto;
import cz.upce.vetalmael.repository.ClinicConsumableRepository;
import cz.upce.vetalmael.service.ClinicConsumableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service(value = "clinicConsumableService")
@Transactional
public class ClinicConsumableServiceImpl implements ClinicConsumableService {

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    private ClinicConsumableRepository clinicConsumableRepository;

    @Override
    public ClinicConsumable addClinicConsumable(ClinicConsumableDto clinicConsumableDto, int idClinic, int idConsumable) throws Exception{
        ClinicConsumable clinicConsumable = new ClinicConsumable();
        if(clinicConsumableRepository.countAllByClinic_IdClinicAndConsumable_IdConsumable(idClinic,idConsumable) == 0) {
            clinicConsumable.setQuantityInStock(clinicConsumableDto.getQuantityInStock());
            Clinic clinic = entityManager.getReference(Clinic.class, idClinic);
            clinic.setIdClinic(idClinic);
            Consumable consumable = entityManager.getReference(Consumable.class, idConsumable);
            consumable.setIdConsumable(idConsumable);
            clinicConsumable.setClinic(clinic);
            clinicConsumable.setConsumable(consumable);
            return clinicConsumableRepository.save(clinicConsumable);
        }
        throw new Exception("Can't add same consumable to clinic");
    }

    @Override
    public ClinicConsumable editClinicConsumable(ClinicConsumableDto clinicConsumableDto, int idClinicConsumable) {
        ClinicConsumable one = clinicConsumableRepository.getOne(idClinicConsumable);
        one.setQuantityInStock(clinicConsumableDto.getQuantityInStock());
        return clinicConsumableRepository.save(one);
    }

    @Override
    public void removeClinicConsumable(int idClinicConsumable) {
        clinicConsumableRepository.deleteById(idClinicConsumable);
    }

    @Override
    public ClinicConsumable getClinicConsumable(int idClinic, int idConsumable) {
        return clinicConsumableRepository.findByClinic_IdClinicAndConsumable_IdConsumable(idClinic,idConsumable);
    }

    @Override
    public List<ClinicConsumable> getClinicConsumables(int idClinic) {
        return clinicConsumableRepository.findAllByClinic_IdClinic(idClinic);
    }


}
