package com.clinic.vaxschedular.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.clinic.vaxschedular.DTO.LoginDTO;
import com.clinic.vaxschedular.DTO.Reservation_DTO;
import com.clinic.vaxschedular.Entity.Patient;
import com.clinic.vaxschedular.Entity.Patient_Vaccine;
import com.clinic.vaxschedular.Entity.Role;
import com.clinic.vaxschedular.Entity.Vaccine;
import com.clinic.vaxschedular.Repository.PatientRepo;
import com.clinic.vaxschedular.Repository.Patient_Vaccine_Repo;
import com.clinic.vaxschedular.Repository.RoleRepo;
import com.clinic.vaxschedular.Repository.VaccineRepo;

@Service
public class PaitentServicesImpl implements PaitentServices {

    @Autowired
    private PatientRepo patientRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private VaccineRepo vaccineRepo;

    @Autowired
    private Patient_Vaccine_Repo patient_Vaccine_Repo;

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
                    this.passwordEncoder.encode(patient.getPassword()), patient.getCertification(),
                    patient.getVaccination_Center(),
                    patient.getVaccinationCenter(),
                    patient.getVaccines());
            patientRepo.save(newPatient);
            Role role = new Role("PATIENT", newPatient.getEmail(), newPatient.getPassword());
            roleRepo.save(role);
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

    @Override
    public String reseveVaccination(Reservation_DTO test) {
        Optional<Vaccine> existVaccine = vaccineRepo.findByVaccineName("Vac-X");
        Optional<Patient> existPatient = patientRepo.findByEmail("mohab1@example.com");

        if (existVaccine.isPresent() && existPatient.isPresent()) {
            Patient_Vaccine patient_Vaccine = new Patient_Vaccine();
            patient_Vaccine.setVaccine(existVaccine.get());
            patient_Vaccine.setPatient(existPatient.get());
            patient_Vaccine.setDoses(0);
            patient_Vaccine_Repo.save(patient_Vaccine);
            return "Done!";
        } else {
            return "Failed";
        }

    }

}
