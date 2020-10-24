package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Animal;
import cz.upce.vetalmael.model.Role;
import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.AnimalDto;
import cz.upce.vetalmael.model.dto.ClientDto;
import cz.upce.vetalmael.repository.AnimalRepository;
import cz.upce.vetalmael.service.AnimalService;
import cz.upce.vetalmael.service.LoginService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "animalService")
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;


    /*@Autowired
    private ModelMapper modelMapper;*/

    @Override
    public Animal addAnimal(AnimalDto animalDto) {
        //Animal animal = modelMapper.map(animalDto,Animal.class);
        Animal animal = new Animal();
        animal.setName(animalDto.getName());
        User user = new User();
        user.setIdUser(animalDto.getOwner().getIdUser());
        animal.setOwner(user);
        return animalRepository.save(animal);
    }

    @Override
    public Animal editAnimal(AnimalDto animalDto) {
        //Animal animal = modelMapper.map(animalDto,Animal.class);
        Animal animal = new Animal();
        animal.setIdAnimal(animalDto.getIdAnimal());
        animal.setName(animalDto.getName());
        User user = new User();
        user.setIdUser(animalDto.getOwner().getIdUser());
        animal.setOwner(user);
        return animalRepository.save(animal);
    }

    @Override
    public void removeAnimal(int idAnimal) {
        animalRepository.deleteById(idAnimal);
    }
}
