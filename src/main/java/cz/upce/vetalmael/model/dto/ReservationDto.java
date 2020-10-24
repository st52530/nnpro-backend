package cz.upce.vetalmael.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private int idReservation;
    private Date date;
    private ClientDto clientDto;
    private ClinicDto clinicDto;
}
