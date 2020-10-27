package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Animal;
import cz.upce.vetalmael.model.Message;
import cz.upce.vetalmael.model.Report;
import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.AnimalDto;
import cz.upce.vetalmael.repository.AnimalRepository;
import cz.upce.vetalmael.repository.MessageRepository;
import cz.upce.vetalmael.repository.ReportRepository;
import cz.upce.vetalmael.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "animalService")
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public Animal addAnimal(AnimalDto animalDto, int idUser) {
        Animal animal = new Animal();
        animal.setName(animalDto.getName());
        User user = new User();
        user.setIdUser(idUser);
        animal.setOwner(user);
        return animalRepository.save(animal);
    }

    @Override
    public Animal editAnimal(AnimalDto animalDto, int idAnimal, int idUser) {
        Animal animal = new Animal();
        animal.setIdAnimal(idAnimal);
        animal.setName(animalDto.getName());
        User user = new User();
        user.setIdUser(idUser);
        animal.setOwner(user);
        return animalRepository.save(animal);
    }

    @Override
    public void removeAnimal(int idAnimal) {
        animalRepository.deleteById(idAnimal);
    }

    @Override
    public List<Message> getMessages(int idAnimal) {
        return messageRepository.findAllByAnimal_IdAnimal(idAnimal);
    }

    @Override
    public List<Report> getReports(int idAnimal) {
        return reportRepository.findAllByAnimal_IdAnimal(idAnimal);
    }
}
