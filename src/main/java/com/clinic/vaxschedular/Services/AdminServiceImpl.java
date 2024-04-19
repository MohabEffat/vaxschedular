package com.clinic.vaxschedular.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinic.vaxschedular.Entity.Patient;
import com.clinic.vaxschedular.Repository.PatientRepo;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private PatientRepo patientRepo;

    @Override
    public String removePatient(Patient patient) {
        Optional<Patient> existPatient = patientRepo.findByEmail(patient.getEmail());
        if (existPatient.isPresent()) {
            patientRepo.delete(existPatient.get());
            return "Removed Successfully";
        }
        return "Patient Not Found";
    }

}
