package com.clinic.vaxschedular.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.clinic.vaxschedular.Entity.*;
import com.clinic.vaxschedular.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private VaccinationCenterRepo vaccineCenterRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private VaccineRepo vaccineRepo;

    @Autowired
    private VaccineCenter_Vaccine_Repo vaccineCenter_Vaccine_Repo;

    @Override
    public String removePatient(Patient patient) {
        Optional<Patient> existPatient = patientRepo.findByEmail(patient.getEmail());
        if (existPatient.isPresent()) {
            patientRepo.delete(existPatient.get());
            return "Removed Successfully";
        }
        return "Patient Not Found";
    }

    @Override
    public String addVaccinationCenter(VaccinationCenter vaccinationCenter) {

        Optional<VaccinationCenter> existCenter = vaccineCenterRepo.findByEmail(vaccinationCenter.getEmail());
        if (existCenter.isPresent()) {
            throw new RuntimeException("Email " + vaccinationCenter.getEmail() + " already exist");
        } else {
            vaccinationCenter.setPassword(passwordEncoder.encode(vaccinationCenter.getPassword()));
            vaccineCenterRepo.save(vaccinationCenter);
            Role role = new Role("CENTER", vaccinationCenter.getEmail(), vaccinationCenter.getPassword());
            roleRepo.save(role);
        }
        return "Center Added Successfully!";
    }

    @Override
    public String addAdmin(Admin admin) {
        Optional<Admin> existAdmin = adminRepo.findById(admin.getId());
        if (existAdmin.isPresent()) {
            throw new RuntimeException("Admin Manages Another Center");
        } else {
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            adminRepo.save(admin);
            Role role = new Role("ADMIN", admin.getEmail(), admin.getPassword());
            roleRepo.save(role);
            return "DONE!";
        }
    }

    @Override
    public List<VaccinationCenter> listVaccinationCenter() {

        List<VaccinationCenter> vaccinationCenters = vaccineCenterRepo.findAll();
        vaccinationCenters.forEach(vc -> vc.getPatients().size()); // Trigger lazy loading of patients
        return vaccinationCenters;
    }

    @Override
    public String updateVaccinationCenter(int id, VaccinationCenter vaccinationCenter) {
        Optional<VaccinationCenter> existCenter = vaccineCenterRepo.findById(id);
        if (existCenter.isPresent()) {
            VaccinationCenter updatedCenter = existCenter.get();
            updatedCenter.setAdminId(vaccinationCenter.getAdminId());
            updatedCenter.setCenterName(vaccinationCenter.getCenterName());
            updatedCenter.setPassword(vaccinationCenter.getPassword());
            updatedCenter.setEmail(vaccinationCenter.getEmail());
            updatedCenter.setLocation(vaccinationCenter.getLocation());
            updatedCenter.setPhoneNum(vaccinationCenter.getPhoneNum());
            vaccineCenterRepo.save(updatedCenter);
            return "Updated Successfully";
        } else {
            throw new RuntimeException("Center With id : " + id + " Does not Exist");
        }
    }

    @Override
    public String deleteVaccinationCenter(int id) {
        if (vaccineCenterRepo.findById(id).isPresent()) {
            vaccineCenterRepo.deleteById(id);
            return "Deleted Successfully";
        } else {
            throw new RuntimeException("Center With id : " + id + " Does not Exist");
        }
    }

    @Override
    public String createVaccine(Vaccine vaccine) {
        if (vaccineRepo.findById(vaccine.getId()).isPresent()) {
            throw new RuntimeException("Vaccine Already Exist");
        } else {
            vaccineRepo.save(vaccine);
            return "Added Successfully";
        }
    }

    @Override
    public List<Vaccine> listVaccine() {
        return vaccineRepo.findAll();
    }

    @Override
    public String deleteVaccine(int id) {
        if (vaccineRepo.findById(id).isPresent()) {
            vaccineRepo.deleteById(id);
            return "Deleted Successfully";
        } else {
            throw new RuntimeException("Vaccine With id : " + id + " Does not Exist");
        }
    }

    @Override
    public String updateVaccine(int id, Vaccine vaccine) {
        if (vaccineRepo.findById(id).isPresent()) {
            Vaccine existVaccine = vaccineRepo.findById(id).get();
            existVaccine.setVaccineName(vaccine.getVaccineName());
            existVaccine.setDurationBetweenDoses(vaccine.getDurationBetweenDoses());
            existVaccine.setPrecautions(vaccine.getPrecautions());
            return "Updaed Successfully";
        } else {
            throw new RuntimeException("Vaccine With id : " + id + " Does not Exist");
        }
    }
}
