package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.*;
import cz.upce.vetalmael.model.dto.ClinicConsumableDto;
import cz.upce.vetalmael.repository.ClinicConsumableRepository;
import cz.upce.vetalmael.service.ClinicConsumableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service(value = "clinicConsumableService")
@Transactional
public class ClinicConsumableServiceImpl implements ClinicConsumableService {

    @Autowired
    private ClinicConsumableRepository clinicConsumableRepository;

    @Override
    public ClinicConsumable addClinicConsumable(ClinicConsumableDto clinicConsumableDto, int idClinic, int idConsumable) {
        ClinicConsumable clinicConsumable = new ClinicConsumable();
        clinicConsumable.setQuantityInStock(clinicConsumableDto.getQuantityInStock());
        Clinic clinic = new Clinic();
        clinic.setIdClinic(idClinic);
        Consumable consumable = new Consumable();
        consumable.setIdConsumable(idConsumable);
        clinicConsumable.setClinic(clinic);
        clinicConsumable.setConsumable(consumable);
        return clinicConsumableRepository.save(clinicConsumable);
    }

    @Override
    public ClinicConsumable addQuantityInStock(ClinicConsumableDto clinicConsumableDto, int idClinicConsumable) {
        ClinicConsumable one = clinicConsumableRepository.getOne(idClinicConsumable);
        int quantityInStock = clinicConsumableRepository.getOne(idClinicConsumable).getQuantityInStock();
        one.setQuantityInStock(clinicConsumableDto.getQuantityInStock()+quantityInStock);
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
