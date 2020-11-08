package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.Consumable;
import cz.upce.vetalmael.model.dto.ConsumableDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ConsumableService {
    Consumable addConsumable(ConsumableDto consumableDto);
    List<Consumable> importConsumables(MultipartFile file) throws IOException;
    Consumable editConsumable(ConsumableDto consumableDto, int idConsumable);
    void removeConsumable(int idConsumable);
    Consumable getConsumable(int idConsumable);
    List<Consumable> getConsumables();
}
