package com.clinic.vaxschedular.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.vaxschedular.DTO.LoginDTO;
import com.clinic.vaxschedular.DTO.Reservation_DTO;
import com.clinic.vaxschedular.Entity.Patient;
import com.clinic.vaxschedular.Entity.Patient_Vaccine;
import com.clinic.vaxschedular.Entity.VaccinationCenter;
import com.clinic.vaxschedular.Entity.VaccineCenter_Vaccine;
import com.clinic.vaxschedular.Repository.Patient_Vaccine_Repo;
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

    @PostMapping("/Reserve")
    public String reserve(@RequestBody Reservation_DTO test) {
        return paitentServices.reseveVaccination(test);
    }

    @GetMapping("/List")
    public List<VaccineCenter_Vaccine> listCenters() {
        return paitentServices.listVaccinationCenters();
    }

}
