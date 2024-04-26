package com.clinic.vaxschedular.Controller;

import com.clinic.vaxschedular.Entity.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.clinic.vaxschedular.Entity.Admin;
import com.clinic.vaxschedular.Entity.VaccinationCenter;
import com.clinic.vaxschedular.Services.AdminService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

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

    // --------------------------************************

    @GetMapping("/listAllVaccinationCenters")
    public List<VaccinationCenter> getAllVaccinationCenters() {
        return adminService.listVaccinationCenter();
    }

    @PutMapping("/updateVaccinationCenter/{id}")
    public String updateVaccinationCenter(@PathVariable int id, @RequestBody VaccinationCenter vaccinationCenter) {
        return adminService.updateVaccinationCenter(id, vaccinationCenter);
    }

    @DeleteMapping("/deleteVaccinationCenter/{id}")
    public String deleteVaccinationCenter(@PathVariable int id) {
        return adminService.deleteVaccinationCenter(id);
    }

    @PostMapping("/addVaccine")
    public String createNewVaccine(@RequestBody Vaccine vaccine) {
        return adminService.createVaccine(vaccine);
    }

    // --------------------------************************

    @GetMapping("/listAllVaccines")
    public List<Vaccine> getAllVaccines() {
        return adminService.listVaccine();
    }

    @DeleteMapping("/deleteVaccine/{id}")
    public String deleteVaccine(@PathVariable int id) {
        return adminService.deleteVaccine(id);
    }

    @PutMapping("/updateVaccine/{id}")
    public String updateVaccine(@PathVariable int id, @RequestBody Vaccine vaccine) {
        return adminService.updateVaccine(id, vaccine);
    }
}
