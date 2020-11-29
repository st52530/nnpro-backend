package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.*;

import java.util.List;

public interface ReportV2Service {
    List<Report> getReports();

    Report getReport(Integer id);

    List<Report> getReports(Clinic clinic);

    List<Report> getReports(Animal animal);

    List<Report> getReports(User veterinary);

    Report createNewReport(Report report);

    Report finishReport(Integer reportId, Report report);
}
