package com.clinic.vaxschedular.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.vaxschedular.DTO.LoginDTO;
import com.clinic.vaxschedular.Entity.Patient;
import com.clinic.vaxschedular.Services.PaitentServices;

@RestController
@RequestMapping("/Patient")
public class PatientController {

    @Autowired
    private PaitentServices paitentServices;

    @PostMapping("/Register")
    // localhost:8080/Patient/Register
    public String RegisterPatient(@RequestBody Patient patient) {
        return paitentServices.Register(patient);
    }

    @PostMapping("/login")
    // localhost:8080/Patient/login
    public String loginPatient(@RequestBody LoginDTO loginDTO) {
        return paitentServices.login(loginDTO);
    }

}
