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

    private int idReport;
    private String textDescription;
    private String textDiagnosis;
    private String textRecommendation;
    private AnimalDto animalDto;
    private final ReportState reportState = ReportState.DONE;
    private DiagnosisDto diagnosisDto;
    private OperationDto operationDto;
    private Set<MedicineDto> medicines;
    //Consumables
}


