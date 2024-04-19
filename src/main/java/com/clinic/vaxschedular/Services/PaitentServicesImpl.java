package com.clinic.vaxschedular.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clinic.vaxschedular.DTO.LoginDTO;
import com.clinic.vaxschedular.Entity.Patient;
import com.clinic.vaxschedular.Repository.PatientRepo;

@Service
public class PaitentServicesImpl implements PaitentServices {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String Register(Patient patient) {

        Optional<Patient> optionalPatient = patientRepo.findByEmail(patient.getEmail());

        if (optionalPatient.isPresent()) {
            throw new RuntimeException("Patient with email " + patient.getEmail() + " already exists");
        } else {
            Patient newPatient = new Patient(patient.getId(), patient.getSsn(),
                    patient.getFirstName(), patient.getLastName(), patient.getEmail(),
                    this.passwordEncoder.encode(patient.getPassword()), patient.getVaccinationCenterName(),
                    patient.getVaccineName(), patient.getCertification());
            patientRepo.save(newPatient);
        }
        return "Patient registered successfully!";
    }

    public String login(LoginDTO loginDTO) {
        Optional<Patient> patient = patientRepo.findByEmail(loginDTO.getEmail());
        if (patient.isPresent()) {
            String encodedPassword = patient.get().getPassword();
            boolean isPwdRight = passwordEncoder.matches(loginDTO.getPassword(), encodedPassword);
            if (isPwdRight) {
                Optional<Patient> existPatient = patientRepo.findByEmailAndPassword(loginDTO.getEmail(),
                        encodedPassword);
                if (existPatient.isPresent()) {
                    return "Login Success";
                } else {
                    return "Login Failed";
                }
            } else {
                return "Wrong Password";
            }
        } else {
            return "Email Doesnt Exist";
        }
    }

}
