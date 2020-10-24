package cz.upce.vetalmael.model.dto;

import cz.upce.vetalmael.model.ReportState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReadyReportDto {

    private int idReport;
    private String textDescription;
    private AnimalDto animalDto;
    private final ReportState reportState = ReportState.READY;
}
