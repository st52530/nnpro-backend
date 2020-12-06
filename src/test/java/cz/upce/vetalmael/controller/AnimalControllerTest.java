package cz.upce.vetalmael.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.upce.vetalmael.model.Animal;
import cz.upce.vetalmael.model.Message;
import cz.upce.vetalmael.model.Report;
import cz.upce.vetalmael.model.dto.AnimalDto;
import cz.upce.vetalmael.service.AnimalService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import cz.upce.vetalmael.factories.Factory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static cz.upce.vetalmael.config.SwaggerConfig.SWAGGER_AUTH_KEY;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class AnimalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AnimalService animalService;

    @Test
    @WithMockUser
    void shouldFetchAllAnimals() throws Exception {
        List<Animal> animals = Factory.createAnimals();
        BDDMockito.given(animalService.getAnimals()).willReturn(animals);

        this.mockMvc.perform(get("/animals"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(animals.size())));
    }

    @Test
    @WithMockUser
    void shouldFetchGetAnimal() throws Exception {
        Animal animal = Factory.createAnimal();
        BDDMockito.given(animalService.getAnimal(any(int.class))).willReturn(animal);

        this.mockMvc.perform(get("/animals/{idAnimal}", animal.getIdAnimal()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idAnimal", CoreMatchers.is(animal.getIdAnimal())))
                .andExpect(jsonPath("$.name", CoreMatchers.is(animal.getName())))
                .andExpect(jsonPath("$.owner", CoreMatchers.is(animal.getOwner())));
    }

    @Test
    @WithMockUser
    void shouldFetchAllAnimalsByIdUser() throws Exception {
        final int idUser = 1;
        final Animal animal = Factory.createAnimal();

        given(animalService.addAnimal(any(AnimalDto.class),any(int.class))).willReturn(animal);

        this.mockMvc.perform(post("/animals/user/{idUser}/animal", idUser)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(animal)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idAnimal", CoreMatchers.is(animal.getIdAnimal())))
                .andExpect(jsonPath("$.name", CoreMatchers.is(animal.getName())))
                .andExpect(jsonPath("$.owner", CoreMatchers.is(animal.getOwner())));
    }

    @Test
    @WithMockUser
    void shouldFetchEditAnimalById() throws Exception {
        final int idUser = 1;
        final int idAnimal = 1;
        final Animal animal = Factory.createAnimal();
        final AnimalDto animalDto = Factory.createAnimalDto();

        given(animalService.editAnimal(any(AnimalDto.class),any(int.class),any(int.class))).willReturn(animal);
        this.mockMvc.perform(put("/animals/user/{idUser}/animal/{idAnimal}", idUser, idAnimal)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(animalDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(animal.getName())))
                .andExpect(jsonPath("$.owner", CoreMatchers.is(animal.getOwner())))
                .andExpect(jsonPath("$.idAnimal", CoreMatchers.is(animal.getIdAnimal())));
    }

    @Test
    @WithMockUser
    void shouldReturn404WhenEditAnimalByIdWithNullName() throws Exception {
        final int idUser = 1;
        final int idAnimal = 1;
        final Animal animal = Factory.createAnimal();
        final AnimalDto animalDto = Factory.createAnimalDto();
        animalDto.setName(null);

        given(animalService.editAnimal(any(AnimalDto.class),any(int.class),any(int.class))).willReturn(animal);
        this.mockMvc.perform(put("/animals/user/{idUser}/animal/{idAnimal}", idUser, idAnimal)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(animalDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    void shouldFetchCreateAnimal() throws Exception {
        final int idUser = 1;
        final Animal animal = Factory.createAnimal();
        final AnimalDto animalDto = Factory.createAnimalDto();

        given(animalService.addAnimal(any(AnimalDto.class),any(int.class))).willReturn(animal);
        this.mockMvc.perform(post("/animals/user/{idUser}/animal", idUser)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(animalDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(animal.getName())))
                .andExpect(jsonPath("$.owner", CoreMatchers.is(animal.getOwner())))
                .andExpect(jsonPath("$.idAnimal", CoreMatchers.is(animal.getIdAnimal())));
    }

    @Test
    @WithMockUser
    void shouldReturn404WhenCreateAnimalWithNullName() throws Exception {
        final int idUser = 1;
        final Animal animal = Factory.createAnimal();
        final AnimalDto animalDto = Factory.createAnimalDto();
        animalDto.setName(null);

        given(animalService.addAnimal(any(AnimalDto.class),any(int.class))).willReturn(animal);
        this.mockMvc.perform(post("/animals/user/{idUser}/animal", idUser)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(animalDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    void shouldReturn404WhenCreateAnimalWithNullAnimalDto() throws Exception {
        final int idUser = 1;
        final Animal animal = Factory.createAnimal();

        given(animalService.addAnimal(any(AnimalDto.class),any(int.class))).willReturn(animal);
        this.mockMvc.perform(post("/animals/user/{idUser}/animal", idUser)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(null)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    void shouldFetchDeleteAnimal() throws Exception {
        final int idAnimal = 1;
        doNothing().when(animalService).removeAnimal(any(int.class));

        this.mockMvc.perform(delete("/animals/{idAnimal}", idAnimal))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    void shouldFetch204WhenDeleteAnimalNoContent() throws Exception {
        final int idAnimal = 1;
        doThrow(new EmptyResultDataAccessException(0)).when(animalService).removeAnimal(any(int.class));

        this.mockMvc.perform(delete("/animals/{idAnimal}", idAnimal))
                .andExpect(status().isNotFound());
    }



    @Test
    @WithMockUser
    void shouldFetchGetMessagesByIdAnimal() throws Exception {
        final int idAnimal = 1;
        final List<Message> messages = Factory.createMessages();

        given(animalService.getMessages(any(int.class))).willReturn(messages);

        this.mockMvc.perform(get("/animals/getMessages/{idAnimal}", idAnimal))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(messages.size())));
    }

    @Test
    @WithMockUser
    void shouldFetchGetReportsByIdAnimal() throws Exception {
        final int idAnimal = 1;
        final List<Report> reports = Factory.createReports();

        given(animalService.getReports(any(int.class))).willReturn(reports);

        this.mockMvc.perform(get("/animals/getReports/{idAnimal}", idAnimal))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(reports.size())));
    }
}
