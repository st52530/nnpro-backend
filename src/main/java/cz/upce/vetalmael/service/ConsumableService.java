package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.Consumable;
import cz.upce.vetalmael.model.dto.ConsumableDto;

public interface ConsumableService {
    Consumable addConsumable(ConsumableDto consumableDto);
    Consumable editConsumable(ConsumableDto consumableDto, int idConsumable);
    void removeConsumable(int idConsumable);
}
