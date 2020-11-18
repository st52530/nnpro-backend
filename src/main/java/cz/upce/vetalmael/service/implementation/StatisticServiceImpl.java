package cz.upce.vetalmael.service.implementation;

import cz.upce.vetalmael.model.Report;
import cz.upce.vetalmael.model.dto.DiagnosisStatistic;
import cz.upce.vetalmael.repository.ReportRepository;
import cz.upce.vetalmael.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(value = "statisticService")
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private ReportRepository reportRepository;

    @Override
    public List<DiagnosisStatistic> getMostCommonDiagnosis() {
        List<Report> allByDiagnosisNotNullOrderByDiagnosis = reportRepository.findAllByDiagnosisNotNullOrderByDiagnosis();
        List<DiagnosisStatistic> diagnosisStatistics = new ArrayList<>();
        allByDiagnosisNotNullOrderByDiagnosis.stream().map(Report::getDiagnosis).distinct().forEach(diagnosis -> {
            DiagnosisStatistic diagnosisStatistic = new DiagnosisStatistic();
            diagnosisStatistic.setDiagnosis(diagnosis);
            long count = allByDiagnosisNotNullOrderByDiagnosis.stream().map(Report::getDiagnosis).filter(diagnosis1 -> diagnosis1.equals(diagnosis)).count();
            diagnosisStatistic.setCount((int) count);
            diagnosisStatistics.add(diagnosisStatistic);
        });
        return diagnosisStatistics;
    }

    @Override
    public int getDifferentClientsInMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year,month);
        LocalDate start = yearMonth.atDay(1);
        Date fromDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate end = start.plusMonths( 1 ) ;
        Date endDate = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Report> byDateGreaterThanAndDateLessThan = reportRepository.findByDateGreaterThanAndDateLessThan(fromDate, endDate);
        long count = byDateGreaterThanAndDateLessThan.stream().map(Report::getAnimal).distinct().count();
        return (int)count;
    }

    @Override
    public int getVaccinationCountInMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year,month);
        LocalDate start = yearMonth.atDay(1);
        Date fromDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate end = start.plusMonths( 1 ) ;
        Date endDate = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return reportRepository.countAllByOperation_TypeAndDateGreaterThanAndDateLessThan("Vakcinace", fromDate, endDate);
    }

    @Override
    public int getDifferentClientsInMonthOnClinic(int idClinic, int year, int month) {
        YearMonth yearMonth = YearMonth.of(year,month);
        LocalDate start = yearMonth.atDay(1);
        Date fromDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate end = start.plusMonths( 1 ) ;
        Date endDate = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());
        List<Report> byDateGreaterThanAndDateLessThan = reportRepository.findByVeterinary_Workplace_IdClinicAndDateGreaterThanAndDateLessThan(idClinic,fromDate, endDate);
        long count = byDateGreaterThanAndDateLessThan.stream().map(Report::getAnimal).distinct().count();
        return (int)count;
    }

    @Override
    public int getVaccinationCountInMonthOnClinic(int idClinic, int year, int month) {
        YearMonth yearMonth = YearMonth.of(year,month);
        LocalDate start = yearMonth.atDay(1);
        Date fromDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
        LocalDate end = start.plusMonths( 1 ) ;
        Date endDate = Date.from(end.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return reportRepository.countAllByVeterinary_Workplace_IdClinicAndOperation_TypeAndDateGreaterThanAndDateLessThan(idClinic,"Vakcinace", fromDate, endDate);
    }
}
