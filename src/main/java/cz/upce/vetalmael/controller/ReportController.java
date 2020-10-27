package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.Report;
import cz.upce.vetalmael.model.dto.DoneReportDto;
import cz.upce.vetalmael.model.dto.ReadyReportDto;
import cz.upce.vetalmael.service.ReportService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

import static cz.upce.vetalmael.config.SwaggerConfig.SWAGGER_AUTH_KEY;

@RestController
@RequestMapping("/reports")
@SecurityRequirement(name = SWAGGER_AUTH_KEY)
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/ready")
    public ResponseEntity<Report> addReport(@RequestBody ReadyReportDto readyReportDto) {
        return ResponseEntity.ok(reportService.addReadyReport(readyReportDto));
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping("/ready/{idReadyReport}")
    public ResponseEntity<Report> editReadyReport(@RequestBody ReadyReportDto readyReportDto, @PathVariable int idReadyReport) {
        return ResponseEntity.ok(reportService.editReadyReport(readyReportDto, idReadyReport));
    }

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/done")
    public ResponseEntity<Report> addDoneReport(@RequestBody DoneReportDto readyReportDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();
        return ResponseEntity.ok(reportService.addDoneReport(readyReportDto, username));
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping("/ready/{idReadyReport}/done")
    public ResponseEntity<Report> makeDoneReport(@RequestBody DoneReportDto readyReportDto, @PathVariable int idReadyReport) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();
        return ResponseEntity.ok(reportService.makeReadyReportDone(readyReportDto, idReadyReport, username));
    }

    @Transactional(rollbackOn = Exception.class)
    @PutMapping("/done/{idDoneReport}")
    public ResponseEntity<Report> editDoneReport(@RequestBody DoneReportDto readyReportDto, @PathVariable int idDoneReport) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getPrincipal().toString();
        return ResponseEntity.ok(reportService.editDoneReport(readyReportDto, idDoneReport, username));
    }
}
