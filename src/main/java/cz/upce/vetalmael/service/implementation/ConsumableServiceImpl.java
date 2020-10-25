package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Consumable;
import cz.upce.vetalmael.model.Medicine;
import cz.upce.vetalmael.model.dto.ConsumableDto;
import cz.upce.vetalmael.repository.ConsumableRepository;
import cz.upce.vetalmael.service.ConsumableService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "consumableService")
public class ConsumableServiceImpl implements ConsumableService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ConsumableRepository consumableRepository;

    @Override
    public Consumable addConsumable(ConsumableDto consumableDto) {
        Consumable consumable = modelMapper.map(consumableDto, Consumable.class);
        return consumableRepository.save(consumable);
    }

    @Override
    public Consumable editConsumable(ConsumableDto consumableDto, int idConsumable) {
        Consumable consumable = modelMapper.map(consumableDto, Consumable.class);
        consumable.setIdConsumable(idConsumable);
        return consumableRepository.save(consumable);
    }

    @Override
    public void removeConsumable(int idConsumable) {
        consumableRepository.deleteById(idConsumable);
    }
}
