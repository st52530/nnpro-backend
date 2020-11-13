package cz.upce.vetalmael.model.dto;

import cz.upce.vetalmael.model.Diagnosis;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisStatistic {
    private int count;
    private Diagnosis diagnosis;
}
