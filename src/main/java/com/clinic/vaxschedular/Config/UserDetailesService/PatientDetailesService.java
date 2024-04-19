package com.clinic.vaxschedular.Config.UserDetailesService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.security.core.userdetails.User;
import com.clinic.vaxschedular.Entity.Patient;
import com.clinic.vaxschedular.Repository.PatientRepo;

@Service

public class PatientDetailesService implements UserDetailsService {

    @Autowired
    private PatientRepo patientRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<Patient> patient = patientRepo.findByEmail(email);
        if (patient.isPresent()) {
            var Obj = patient.get();
            return User.builder()
                    .username(Obj.getEmail())
                    .password(Obj.getPassword())
                    .roles("PATIENT")
                    .build();
        } else {
            throw new UsernameNotFoundException(email);
        }
    }

}
