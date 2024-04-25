package com.clinic.vaxschedular.Controller;

import com.clinic.vaxschedular.Entity.Vaccine;
import com.fasterxml.jackson.annotation.JsonRawValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.clinic.vaxschedular.DTO.LoginDTO;
import com.clinic.vaxschedular.Entity.Admin;
import com.clinic.vaxschedular.Entity.Patient;
import com.clinic.vaxschedular.Entity.VaccinationCenter;
import com.clinic.vaxschedular.Repository.VaccinationCenterRepo;
import com.clinic.vaxschedular.Services.AdminService;
import com.clinic.vaxschedular.Services.PaitentServices;

import java.util.List;

@RestController
@RequestMapping(value = "/api/admin/")
public class AdminController {

    @Autowired
    private PaitentServices paitentServices;

    @Autowired
    private AdminService adminService;

    @Autowired
    private VaccinationCenterRepo vaccinationCenterRepo;

    @PostMapping("/Register")
    // localhost:8080/api/admin/Register

    public String RegisterPatient(@RequestBody Patient patient) {
        return paitentServices.Register(patient);
    }

    @PostMapping("/login")
    // localhost:8080/api/admin/login
    public String loginPatient(@RequestBody LoginDTO loginDTO) {
        return paitentServices.login(loginDTO);
    }

    @DeleteMapping("/delete")
    public String removePaitent(@RequestBody Patient patient) {
        return adminService.removePatient(patient);
    }

    @GetMapping("/hello")
    public String sayWelcome() {
        return "Welcome";
    }

    @PostMapping("/Add_Center")
    public String add_Center(@RequestBody VaccinationCenter vaccinationCenter) {

        return adminService.addVaccinationCenter(vaccinationCenter);
    }

    @PostMapping("/Add_Admin")
    public String add_Aenter(@RequestBody Admin admin) {

        return adminService.addAdmin(admin);
    }

    @GetMapping(value = "listAllVaccinationCenters")
    public List<VaccinationCenter> getAllVaccinationCenters()
    {
        return adminService.listVaccinationCenter();
    }

    @PutMapping("ubdateVaccinationCenter")
    public String ubdateVaccinationCenter(@RequestBody VaccinationCenter vaccinationCenter)
    {
        if (adminService.updateVaccinationCenter(vaccinationCenter))
            return "Center Is Ubdated Successfully";
        return "This Center is NOT Exict";
    }

    @DeleteMapping("deleteVaccinationCenter")
    public String deleteVaccinationCenter(@RequestBody int id)
    {
        if (adminService.deleteVaccinationCenter(id))
            return id + " is Deleted Successfully";
        return "This Center is NOT Exict";
    }


     @PostMapping("addVaccine")
     public String createNewVaccine(@RequestBody Vaccine vaccine) {
     if (adminService.createVaccine(vaccine)) {
        return "Vaccine is Created";
     }
     return "This Vaccine is already Exist";
     }

     @GetMapping(value = "listAllVaccines")
     public List<Vaccine> getAllVaccines() {
     return adminService.listVaccine();
     }
     @DeleteMapping("deleteVaccine")
     public String deleteVaccine(@RequestBody int id)
     {
         if(adminService.deleteVaccine(id))
         {
             return id + " is Deleted Successfully";
         }
         return "This Vaccine is NOT Exict";
     }

     @PutMapping("ubdateVaccine")
     public String ubdateVaccine(@RequestBody Vaccine vaccine) {
     if (adminService.updateVaccine(vaccine)) {
     return "Vaccine Is Ubdated Successfully";
     }
     return "This Vaccine Is NOT Exist";
     }
}
