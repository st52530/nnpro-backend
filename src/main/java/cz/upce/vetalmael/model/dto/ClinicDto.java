package cz.upce.vetalmael.model.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClinicDto {

    private int idClinic;
    private String name;
    private String address;

}
