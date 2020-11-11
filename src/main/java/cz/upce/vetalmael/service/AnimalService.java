package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.Animal;
import cz.upce.vetalmael.model.Message;
import cz.upce.vetalmael.model.Report;
import cz.upce.vetalmael.model.dto.AnimalDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AnimalService {

    Animal addAnimal(AnimalDto animalDto, int idUser);

    Animal editAnimal(AnimalDto animalDto, int idAnimal, int idUser);

    void removeAnimal(int idAnimal);

    List<Message> getMessages(int idAnimal);

    List<Report> getReports(int idAnimal);

    Animal getAnimal(int idAnimal);

    List<Animal> getAnimals();

}
