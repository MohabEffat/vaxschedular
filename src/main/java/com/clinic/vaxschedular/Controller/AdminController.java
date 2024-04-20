package com.clinic.vaxschedular.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.clinic.vaxschedular.DTO.LoginDTO;
import com.clinic.vaxschedular.Entity.Admin;
import com.clinic.vaxschedular.Entity.Patient;
import com.clinic.vaxschedular.Entity.VaccinationCenter;
import com.clinic.vaxschedular.Repository.VaccinationCenterRepo;
import com.clinic.vaxschedular.Services.AdminService;
import com.clinic.vaxschedular.Services.PaitentServices;

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

    // @GetMapping(value = "listAllVaccinationCenters")

    // public List<VaccinationCenter> getAllVaccinationCenters() {
    // return vaccinationCenterService.findAll();
    // }

    // @GetMapping(value = "listAllVaccines")

    // public List<Vaccine> getAllVaccines() {
    // return vaccineService.findAll();
    // }

    // @PostMapping("addVaccinationCenter")
    // public String createNewVaccinationCenter(@RequestBody VaccinationCenter
    // vaccinationCenter) {
    // if (vaccinationCenterService.save(vaccinationCenter)) {
    // return "VaccinationCenter is Created";
    // }
    // return "null";
    // }

    // @PostMapping("addVaccine")
    // public String createNewVaccine(@RequestBody Vaccine vaccine) {
    // if (vaccineService.save(vaccine)) {
    // return "Vaccine is Created";
    // }
    // return "null";
    // }

    // @PutMapping("ubdateVaccinationCenter")
    // public String ubdateVaccinationCenter(@RequestBody VaccinationCenter
    // vaccinationCenter) {
    // if (vaccinationCenterService.ubdate(vaccinationCenter)) {
    // return "Ubdated Successfully";
    // }
    // return "null";
    // }

    // @PutMapping("ubdateVaccine")
    // public String ubdateVaccine(@RequestBody Vaccine vaccine) {
    // if (vaccineService.ubdate(vaccine)) {
    // return "Ubdated Successfully";
    // }
    // return "null";
    // }

    // @DeleteMapping("deleteVaccinationCenter")
    // public void deleteVaccinationCenter(@RequestBody VaccinationCenter
    // vaccinationCenter) {
    // vaccinationCenterService.delete(vaccinationCenter);
    // }

    // @DeleteMapping("deleteVaccine")
    // public void deleteVaccine(@RequestBody Vaccine vaccine) {
    // vaccineService.delete(vaccine);
    // }

}
