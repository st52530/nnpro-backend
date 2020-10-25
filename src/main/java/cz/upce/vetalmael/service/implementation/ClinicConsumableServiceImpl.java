package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.*;
import cz.upce.vetalmael.model.dto.ClinicConsumableDto;
import cz.upce.vetalmael.repository.ClinicConsumableRepository;
import cz.upce.vetalmael.service.ClinicConsumableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "clinicConsumableService")
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
        ClinicConsumable clinicConsumable = new ClinicConsumable();
        int quantityInStock = clinicConsumableRepository.getOne(idClinicConsumable).getQuantityInStock();
        clinicConsumable.setQuantityInStock(clinicConsumableDto.getQuantityInStock()+quantityInStock);
        return clinicConsumableRepository.save(clinicConsumable);
    }

    @Override
    public void removeClinicConsumable(int idClinicConsumable) {
        clinicConsumableRepository.deleteById(idClinicConsumable);
    }
}
