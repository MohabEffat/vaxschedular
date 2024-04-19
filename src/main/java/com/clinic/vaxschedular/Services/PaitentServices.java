package com.clinic.vaxschedular.Services;

import org.springframework.stereotype.Service;

import com.clinic.vaxschedular.DTO.LoginDTO;
// import com.clinic.vaxschedular.DTO.Certificate;
// import com.clinic.vaxschedular.Entity.Vaccine;
import com.clinic.vaxschedular.Entity.Patient;

@Service
public interface PaitentServices {

    String Register(Patient patient);

    String login(LoginDTO loginDTO);

    // String listVaccinationCenters();

    // String listVaccine();

    // String reseveVaccination(Vaccine vaccine);

    // String viewCertification(Certificate certificate);

}
