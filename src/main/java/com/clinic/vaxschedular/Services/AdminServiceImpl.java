package com.clinic.vaxschedular.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clinic.vaxschedular.Entity.Admin;
import com.clinic.vaxschedular.Entity.Patient;
import com.clinic.vaxschedular.Entity.VaccinationCenter;
import com.clinic.vaxschedular.Repository.AdminRepo;
import com.clinic.vaxschedular.Repository.PatientRepo;
import com.clinic.vaxschedular.Repository.VaccinationCenterRepo;

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
        }
        return "Center Added Successfully!";
    }

    public String addAdmin(Admin admin) {
        Optional<Admin> existAdmin = adminRepo.findById(admin.getId());
        if (existAdmin.isPresent()) {
            throw new RuntimeException("Admin Manages Another Center");
        } else {
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            adminRepo.save(admin);
            return "DONE!";
        }
    }

}
