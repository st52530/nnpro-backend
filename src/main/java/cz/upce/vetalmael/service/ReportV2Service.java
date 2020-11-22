package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.Animal;
import cz.upce.vetalmael.model.Clinic;
import cz.upce.vetalmael.model.Report;
import cz.upce.vetalmael.model.ReportState;

import java.util.List;

public interface ReportV2Service {
    List<Report> getReports();

    List<Report> getReports(Clinic clinic, ReportState state);

    List<Report> getReports(Animal animal, ReportState state);

    Report createNewReport(Report report);

    Report finishReport(Integer reportId, Report report);
}
