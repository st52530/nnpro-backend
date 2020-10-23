package cz.upce.vetalmael.controller;

import cz.upce.vetalmael.model.dto.ClinicDto;
import cz.upce.vetalmael.service.ClinicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/clinic")
public class ClinicController {

    @Autowired
    private ClinicService clinicService;

    @Transactional(rollbackOn = Exception.class)
    @PostMapping("/add-clinic")
    public void addClinic(@RequestBody ClinicDto clinicDto) {
        clinicService.addClinic(clinicDto);
    }

    @PostMapping("/remove-clinic/{idClinic}")
    public void removeClinic(@PathVariable int idClinic) {
        clinicService.removeClinic(idClinic);
    }
}
