package cz.upce.vetalmael.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.upce.vetalmael.factories.Factory;
import cz.upce.vetalmael.model.*;
import cz.upce.vetalmael.model.dto.AnimalDto;
import cz.upce.vetalmael.model.dto.ClinicConsumableDto;
import cz.upce.vetalmael.model.dto.ClinicDto;
import cz.upce.vetalmael.model.dto.ClinicMedicineDto;
import cz.upce.vetalmael.service.AnimalService;
import cz.upce.vetalmael.service.ClinicConsumableService;
import cz.upce.vetalmael.service.ClinicMedicineService;
import cz.upce.vetalmael.service.ClinicService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClinicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ClinicService clinicService;

    @MockBean
    private ClinicMedicineService clinicMedicineService;

    @MockBean
    private ClinicConsumableService clinicConsumableService;

    @Test
    @WithMockUser
    void shouldFetchCreateClinic() throws Exception {
        final Clinic clinic = Factory.createClinic();
        final ClinicDto clinicDto = Factory.createClinicDto();

        given(clinicService.addClinic(any(ClinicDto.class))).willReturn(clinic);
        this.mockMvc.perform(post("/clinics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clinicDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(clinic.getName())))
                .andExpect(jsonPath("$.idClinic", CoreMatchers.is(clinic.getIdClinic())));
    }

    @Test
    @WithMockUser
    void shouldFetchEditClinic() throws Exception {
        final int idClinic = 1;
        final Clinic clinic = Factory.createClinic();
        final ClinicDto clinicDto = Factory.createClinicDto();

        given(clinicService.editClinic(any(ClinicDto.class),any(int.class))).willReturn(clinic);
        this.mockMvc.perform(put("/clinics/{idClinic}",idClinic)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clinicDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(clinic.getName())))
                .andExpect(jsonPath("$.idClinic", CoreMatchers.is(clinic.getIdClinic())));
    }

    @Test
    @WithMockUser
    void shouldFetchDeleteClinic() throws Exception {
        final int idClinic = 1;
        doNothing().when(clinicService).removeClinic(any(int.class));

        this.mockMvc.perform(delete("/clinics/{idClinic}", idClinic))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    void shouldFetch404WhenDeleteClinicNoContent() throws Exception {
        final int idClinic = 1;
        doThrow(new EmptyResultDataAccessException(0)).when(clinicService).removeClinic(any(int.class));

        this.mockMvc.perform(delete("/{idClinic}", idClinic))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void shouldFetchGetAllClinics() throws Exception {
        final List<Clinic> clinics = Factory.createClinics();

        given(clinicService.getClinics()).willReturn(clinics);

        this.mockMvc.perform(get("/clinics"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(clinics.size())));
    }

    @Test
    @WithMockUser
    void shouldFetchGetAnimal() throws Exception {
        final Clinic clinic = Factory.createClinic();

        BDDMockito.given(clinicService.getClinic(any(int.class))).willReturn(clinic);

        this.mockMvc.perform(get("/clinics/{idClinic}", clinic.getIdClinic()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", CoreMatchers.is(clinic.getName())))
                .andExpect(jsonPath("$.idClinic", CoreMatchers.is(clinic.getIdClinic())));
    }

    @Test
    @WithMockUser
    void shouldFetchAddMedicineToClinic() throws Exception {
        final int idMedicine = 0;
        final Clinic clinic = Factory.createClinic();
        final ClinicMedicineDto clinicMedicineDto = Factory.createClinicMedicineDto();
        final ClinicMedicine clinicMedicine = Factory.createClinicMedicine();


        given(clinicMedicineService.addClinicMedicine(any(ClinicMedicineDto.class),any(int.class),any(int.class))).willReturn(clinicMedicine);
        this.mockMvc.perform(post("/clinics/{idClinic}/clinic-medicine/medicine/{idMedicine}", clinic.getIdClinic(), idMedicine)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clinicMedicineDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idClinicMedicine", CoreMatchers.is(clinicMedicine.getIdClinicMedicine())))
                .andExpect(jsonPath("$.quantityInStock", CoreMatchers.is(clinicMedicine.getQuantityInStock())));
    }

    @Test
    @WithMockUser
    void shouldFetch400WhenAddMedicineToNotExistClinic() throws Exception {
        final int idMedicine = 0;
        final Clinic clinic = Factory.createClinic();

        doThrow(new Exception()).when(clinicMedicineService).addClinicMedicine(any(ClinicMedicineDto.class),any(int.class),any(int.class));

        this.mockMvc.perform(post("/clinics/{idClinic}/clinic-medicine/medicine/{idMedicine}", clinic.getIdClinic(), idMedicine))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    void shouldFetchAddMedicineQuantityInStock() throws Exception {
        final int idClinicMedicine = 0;
        final ClinicMedicineDto clinicMedicineDto = Factory.createClinicMedicineDto();
        final ClinicMedicine clinicMedicine = Factory.createClinicMedicine();

        given(clinicMedicineService.editClinicMedicine(any(ClinicMedicineDto.class),any(int.class))).willReturn(clinicMedicine);
        this.mockMvc.perform(put("/clinics/clinic-medicine/{idClinicMedicine}", idClinicMedicine)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clinicMedicineDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idClinicMedicine", CoreMatchers.is(clinicMedicine.getIdClinicMedicine())))
                .andExpect(jsonPath("$.quantityInStock", CoreMatchers.is(clinicMedicine.getQuantityInStock())));
    }

    @Test
    @WithMockUser
    void removeClinicMedicine() throws Exception {
        final int idClinicMedicine = 1;
        doNothing().when(clinicMedicineService).removeClinicMedicine(any(int.class));

        this.mockMvc.perform(delete("/clinics/clinic-medicine/{idClinicMedicine}", idClinicMedicine))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    void shouldFetch404WhenRemoveClinicMedicine() throws Exception {
        final int idClinicMedicine = 1;
        doThrow(new EmptyResultDataAccessException(0)).when(clinicMedicineService).removeClinicMedicine(any(int.class));

        this.mockMvc.perform(delete("/clinics/clinic-medicine/{idClinicMedicine}", idClinicMedicine))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void removeClinicMedicineWitchOutParam() throws Exception {
        doNothing().when(clinicMedicineService).removeClinicMedicine(any(int.class));

        this.mockMvc.perform(delete("/clinics/clinic-medicine"))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    void shouldFetch404WhenRemoveClinicMedicineWitchOutParam() throws Exception {
        doThrow(new EmptyResultDataAccessException(0)).when(clinicMedicineService).removeClinicMedicine();

        this.mockMvc.perform(delete("/clinics/clinic-medicine"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void shouldFetchAddConsumableToClinic() throws Exception {
        final ClinicConsumable clinicConsumable = Factory.createClinicConsumable();
        final int idClinic = 0;
        final ClinicMedicineDto clinicMedicineDto = Factory.createClinicMedicineDto();
        final ClinicMedicine clinicMedicine = Factory.createClinicMedicine();

        given(clinicConsumableService.addClinicConsumable(any(ClinicConsumableDto.class),any(int.class),any(int.class))).willReturn(clinicConsumable);
        this.mockMvc.perform(post("/clinics/{idClinic}/clinic-consumable/consumable/{idConsumable}", idClinic, clinicMedicine.getIdClinicMedicine())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clinicMedicineDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idClinicConsumable", CoreMatchers.is(clinicConsumable.getIdClinicConsumable())))
                .andExpect(jsonPath("$.quantityInStock", CoreMatchers.is(clinicConsumable.getQuantityInStock())));
    }

    @Test
    @WithMockUser
    void shouldFetch404WhenAddConsumableToClinic() throws Exception {
        final int idClinic = 1;
        final int idClinicMedicine = 1;
        doThrow(new EmptyResultDataAccessException(0)).when(clinicConsumableService).addClinicConsumable(any(ClinicConsumableDto.class),any(int.class),any(int.class));
        this.mockMvc.perform(post("/clinics/{idClinic}/clinic-consumable/consumable/{idConsumable}", idClinic, idClinicMedicine))
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser
    void shouldFetchAddConsumableQuantityInStock() throws Exception {
        final ClinicConsumable clinicConsumable = Factory.createClinicConsumable();
        final ClinicMedicineDto clinicMedicineDto = Factory.createClinicMedicineDto();
        final ClinicMedicine clinicMedicine = Factory.createClinicMedicine();

        given(clinicConsumableService.editClinicConsumable(any(ClinicConsumableDto.class),any(int.class))).willReturn(clinicConsumable);
        this.mockMvc.perform(put("/clinics/clinic-consumable/{idClinicConsumable}", clinicMedicine.getIdClinicMedicine())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(clinicMedicineDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idClinicConsumable", CoreMatchers.is(clinicConsumable.getIdClinicConsumable())))
                .andExpect(jsonPath("$.quantityInStock", CoreMatchers.is(clinicConsumable.getQuantityInStock())));
    }

    @Test
    @WithMockUser
    void removeClinicConsumable() throws Exception {
        final int idClinicConsumable = 1;
        doNothing().when(clinicConsumableService).removeClinicConsumable(any(int.class));

        this.mockMvc.perform(delete("/clinics/clinic-consumable/{idClinicConsumable}", idClinicConsumable))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser
    void shouldFetch404WhenClinicConsumable() throws Exception {
        final int idClinicConsumable = 1;
        doThrow(new EmptyResultDataAccessException(0)).when(clinicConsumableService).removeClinicConsumable(any(int.class));

        this.mockMvc.perform(delete("/clinics/clinic-consumable/{idClinicConsumable}", idClinicConsumable))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void shouldFetchGetConsumablesInClinic() throws Exception {
        final ClinicConsumable clinicConsumable = Factory.createClinicConsumable();
        final int idClinic = 1;
        final int idConsumable = 1;

        given(clinicConsumableService.getClinicConsumable(any(int.class),any(int.class))).willReturn(clinicConsumable);
        this.mockMvc.perform(get("/clinics/{idClinic}/clinic-consumable/consumable/{idConsumable}", idClinic, idConsumable))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idClinicConsumable", CoreMatchers.is(clinicConsumable.getIdClinicConsumable())))
                .andExpect(jsonPath("$.quantityInStock", CoreMatchers.is(clinicConsumable.getQuantityInStock())));
    }

    @Test
    @WithMockUser
    void shouldFetchGetAllConsumablesInClinic() throws Exception {
        final int idClinic = 1;
        final List<ClinicConsumable> clinicConsumables = Factory.createClinicConsumables();

        given(clinicConsumableService.getClinicConsumables(any(int.class))).willReturn(clinicConsumables);

        this.mockMvc.perform(get("/clinics/{idClinic}/clinic-consumable/consumables", idClinic))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(clinicConsumables.size())));
    }

    @Test
    @WithMockUser
    void shouldFetchGetAllMedicinesInClinic() throws Exception {
        final int idClinic = 1;
        final List<ClinicMedicine> clinicMedicines = Factory.createClinicMedicines();

        given(clinicMedicineService.getClinicMedicines(any(int.class))).willReturn(clinicMedicines);

        this.mockMvc.perform(get("/clinics/{idClinic}/clinic-medicine/medicines", idClinic))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", CoreMatchers.is(clinicMedicines.size())));
    }

    @Test
    @WithMockUser
    void shouldFetchGetMedicinesInClinic() throws Exception {
        final ClinicMedicine clinicMedicine = Factory.createClinicMedicine();
        final int idClinic = 1;
        final int idMedicine = 1;

        given(clinicMedicineService.getClinicMedicine(any(int.class),any(int.class))).willReturn(clinicMedicine);
        this.mockMvc.perform(get("/clinics/{idClinic}/clinic-medicine/medicine/{idMedicine}", idClinic, idMedicine))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.quantityInStock", CoreMatchers.is(clinicMedicine.getQuantityInStock())))
                .andExpect(jsonPath("$.idClinicMedicine", CoreMatchers.is(clinicMedicine.getIdClinicMedicine())));
    }





}
