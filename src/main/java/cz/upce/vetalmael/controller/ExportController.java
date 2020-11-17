package cz.upce.vetalmael.controller;

import com.itextpdf.text.Document;
import cz.upce.vetalmael.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/export")
public class ExportController {

    @Autowired
    ExportService exportService;

    @GetMapping(value = "/{idReport}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<?> getPdf(@PathVariable int idReport) {
        ByteArrayInputStream bis = exportService.exportToPdf(idReport);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=export.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
