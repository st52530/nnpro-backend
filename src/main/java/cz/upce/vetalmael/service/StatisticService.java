package cz.upce.vetalmael.service;

import cz.upce.vetalmael.model.Diagnosis;
import cz.upce.vetalmael.model.dto.DiagnosisStatistic;

import java.util.List;

public interface StatisticService {

    List<DiagnosisStatistic> getMostCommonDiagnosis();

    int getDifferentClientsInMonth(int year, int month);

    int getVaccinationCountInMonth(int year, int month);

    int getDifferentClientsInMonthOnClinic(int idClinic,int year, int month);

    int getVaccinationCountInMonthOnClinic(int idClinic,int year, int month);
}
