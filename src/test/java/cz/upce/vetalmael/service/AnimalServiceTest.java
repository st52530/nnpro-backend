package cz.upce.vetalmael.service;

import cz.upce.vetalmael.factories.Factory;
import cz.upce.vetalmael.model.Animal;
import cz.upce.vetalmael.model.Message;
import cz.upce.vetalmael.model.Report;
import cz.upce.vetalmael.model.User;
import cz.upce.vetalmael.model.dto.AnimalDto;
import cz.upce.vetalmael.repository.AnimalRepository;
import cz.upce.vetalmael.repository.MessageRepository;
import cz.upce.vetalmael.repository.ReportRepository;
import cz.upce.vetalmael.service.implementation.AnimalServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {EntityManager.class})
@ExtendWith(MockitoExtension.class)
public class AnimalServiceTest {
    @MockBean
    EntityManager entityManager;

    @Mock
    private AnimalRepository animalRepository;

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private ReportRepository reportRepository;

    @InjectMocks
    private AnimalServiceImpl animalService;

    @Test
    public void testAddAnimal() {
        final User user = Factory.createUser();
        user.setIdUser(1);
        final Animal animal = Factory.createAnimal();
        final AnimalDto animalDto = Factory.createAnimalDto();

        given(animalRepository.save(any(Animal.class))).willReturn(animal);
        given(entityManager.getReference(any(),any(int.class))).willReturn(user);

        Animal expected = animalService.addAnimal(animalDto, user.getIdUser());

        assertThat(expected).isNotNull();
        assertEquals(expected.getIdAnimal(), animal.getIdAnimal());

        verify(animalRepository).save(any(Animal.class));
    }

    @Test
    public void testEditAnimal() {
        final User user = new User();
        user.setIdUser(1);
        final Animal animal = Factory.createAnimal();
        final AnimalDto animalDto = Factory.createAnimalDto();

        given(animalRepository.save(any(Animal.class))).willReturn(animal);
        given(animalRepository.getOne(any(int.class))).willReturn(animal);
        given(entityManager.getReference(any(),any(int.class))).willReturn(user);

        Animal expected = animalService.editAnimal(animalDto, animal.getIdAnimal(), user.getIdUser());

        assertThat(expected).isNotNull();
        assertEquals(expected.getIdAnimal(), animal.getIdAnimal());
        assertEquals(expected.getOwner().getIdUser(), user.getIdUser());
        assertEquals(expected.getName(), animalDto.getName());

        verify(animalRepository).save(any(Animal.class));
    }

    @Test
    void testRemoveAnimal() {
        final int id = 1;

        animalService.removeAnimal(id);
        animalService.removeAnimal(id);

        verify(animalRepository, times(2)).deleteById(id);
    }

    @Test
    void testGetAnimal(){
        final Animal animal = Factory.createAnimal();

        given(animalRepository.getOne(animal.getIdAnimal())).willReturn(animal);

        final Animal expected  = animalService.getAnimal(animal.getIdAnimal());

        assertThat(expected).isNotNull();
        assertEquals(expected, animal);
    }


    @Test
    void testGetAnimals(){
        final List<Animal> animals = Factory.createAnimals();

        given(animalRepository.findAll()).willReturn(animals);

        final List<Animal> expected  = animalService.getAnimals();

        assertThat(expected).isNotNull();
        assertEquals(expected, animals);
        for (int i = 0; i < animals.size(); i++) {
            assertEquals(expected.get(i), animals.get(i));
            assertEquals(expected.get(i).getName(), animals.get(i).getName());
            assertEquals(expected.get(i).getIdAnimal(), animals.get(i).getIdAnimal());
        }
    }


    @Test
    void testGetReports(){
        final List<Report> reports = Factory.createReports();

        given(reportRepository.findAllByAnimal_IdAnimal(any(int.class))).willReturn(reports);

        final List<Report> expected  = animalService.getReports(0);

        assertThat(expected).isNotNull();
        assertEquals(expected, reports);
        for (int i = 0; i < reports.size(); i++) {
            assertEquals(expected.get(i), reports.get(i));
            assertEquals(expected.get(i).getAnimal(), reports.get(i).getAnimal());
            assertEquals(expected.get(i).getTextDescription(), reports.get(i).getTextDescription());
        }
    }


    @Test
    void testGetMessages(){
        final List<Message> reports = Factory.createMessages();

        given(messageRepository.findAllByAnimal_IdAnimal(any(int.class))).willReturn(reports);

        final List<Message> expected  = animalService.getMessages(0);

        assertThat(expected).isNotNull();
        assertEquals(expected, reports);
        for (int i = 0; i < reports.size(); i++) {
            assertEquals(expected.get(i), reports.get(i));
            assertEquals(expected.get(i).getText(), reports.get(i).getText());
        }
    }
}
