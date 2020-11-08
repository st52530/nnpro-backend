package cz.upce.vetalmael.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisDto {

    private String name;
    private String type;
    private String targetAnimals;
    private String symptoms;
    private String incubationPeriod;
    private String treatment;
    private String prevention;
}
