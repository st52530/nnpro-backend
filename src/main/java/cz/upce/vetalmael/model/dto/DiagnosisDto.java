package cz.upce.vetalmael.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisDto {

    private int idDiagnosis;
    private String name;
    private String type;

}
