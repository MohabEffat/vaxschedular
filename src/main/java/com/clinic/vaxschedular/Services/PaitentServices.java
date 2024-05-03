package com.clinic.vaxschedular.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.clinic.vaxschedular.DTO.LoginDTO;
import com.clinic.vaxschedular.DTO.Reservation_DTO;
// import com.clinic.vaxschedular.DTO.Certificate;
// import com.clinic.vaxschedular.Entity.Vaccine;
import com.clinic.vaxschedular.Entity.Patient;
import com.clinic.vaxschedular.Entity.VaccinationCenter;
import com.clinic.vaxschedular.Entity.Vaccine;
import com.clinic.vaxschedular.Entity.VaccineCenter_Vaccine;

@Service
public interface PaitentServices {

    String Register(Patient patient);

    String login(LoginDTO loginDTO);

    List<VaccineCenter_Vaccine> listVaccinationCenters();

    // String listVaccine();

    String reseveVaccination(Reservation_DTO test);

    // String viewCertification(Certificate certificate);

}
