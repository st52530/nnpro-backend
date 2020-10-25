package cz.upce.vetalmael.model.dto;

import cz.upce.vetalmael.model.ReportState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoneReportDto {

    private String textDescription;
    private String textDiagnosis;
    private String textRecommendation;
    private int idAnimal;
    private final ReportState reportState = ReportState.DONE;
    private Integer idDiagnosis;
    private Integer idOperation;
    private Set<Integer> setOfIdMedicines;
    private Set<Integer> setOfIdConsumables;
}


