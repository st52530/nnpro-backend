package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.Report;
import cz.upce.vetalmael.model.dto.DoneReportDto;
import cz.upce.vetalmael.model.dto.ReadyReportDto;

public interface ReportService {

    Report addReadyReport(ReadyReportDto reportDto);
    Report editReadyReport(ReadyReportDto reportDto);
    Report addDoneReport(DoneReportDto reportDto, String veterinaryUsername);
    Report editDoneReport(DoneReportDto reportDto, String veterinaryUsername);
}
