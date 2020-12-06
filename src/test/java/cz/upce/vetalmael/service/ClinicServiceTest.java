package cz.upce.vetalmael.service;

import cz.upce.vetalmael.factories.Factory;
import cz.upce.vetalmael.model.Clinic;
import cz.upce.vetalmael.model.dto.ClinicDto;
import cz.upce.vetalmael.repository.ClinicRepository;
import cz.upce.vetalmael.service.implementation.ClinicServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ClinicServiceTest {
    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ClinicRepository clinicRepository;

    @InjectMocks
    private ClinicServiceImpl clinicService;

    @Test
    public void testAddClinic() {
        final Clinic clinic = Factory.createClinic();
        final ClinicDto clinicDto = Factory.createClinicDto();

        given(modelMapper.map(any(ClinicDto.class), any())).willReturn(clinic);
        given(clinicRepository.save(clinic)).willReturn(clinic);

        Clinic expected = clinicService.addClinic(clinicDto);

        assertThat(expected).isNotNull();
        assertEquals(expected.getIdClinic(), clinic.getIdClinic());
        assertEquals(expected.getName(), clinic.getName());

        verify(clinicRepository).save(any(Clinic.class));
    }

    @Test
    public void testEditClinic() {
        final Clinic clinic = Factory.createClinic();
        final ClinicDto clinicDto = Factory.createClinicDto();

        given(clinicRepository.getOne(any(int.class))).willReturn(clinic);
        given(clinicRepository.save(clinic)).willReturn(clinic);

        Clinic expected = clinicService.editClinic(clinicDto, 0);

        assertThat(expected).isNotNull();
        assertEquals(expected.getIdClinic(), clinic.getIdClinic());
        assertEquals(expected.getName(), clinic.getName());

        verify(clinicRepository).save(any(Clinic.class));
    }

    @Test
    public void testRemoveClinic() {
        final int id = 1;

        clinicService.removeClinic(id);
        clinicService.removeClinic(id);

        verify(clinicRepository, times(2)).deleteById(id);
    }

    @Test
    void testGetClinic(){
        final Clinic clinic = Factory.createClinic();

        given(clinicRepository.getOne(clinic.getIdClinic())).willReturn(clinic);

        final Clinic expected  = clinicService.getClinic(clinic.getIdClinic());

        assertThat(expected).isNotNull();
        assertEquals(expected, clinic);
        assertEquals(expected.getIdClinic(), clinic.getIdClinic());
        assertEquals(expected.getName(), clinic.getName());
    }

    @Test
    void testGetClinics(){
        final List<Clinic> clinics = Factory.createClinics();

        given(clinicRepository.findAll()).willReturn(clinics);

        final List<Clinic> expected  = clinicService.getClinics();

        assertThat(expected).isNotNull();
        assertEquals(expected, clinics);
        for (int i = 0; i < clinics.size(); i++) {
            assertEquals(expected.get(i), clinics.get(i));
            assertEquals(expected.get(i).getIdClinic(), clinics.get(i).getIdClinic());
        }
    }
}
