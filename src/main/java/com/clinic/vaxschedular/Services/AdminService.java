package com.clinic.vaxschedular.Services;

import org.springframework.stereotype.Service;

import com.clinic.vaxschedular.Entity.Admin;
import com.clinic.vaxschedular.Entity.Patient;
import com.clinic.vaxschedular.Entity.VaccinationCenter;
// import com.clinic.vaxschedular.Entity.Vaccine;

@Service
public interface AdminService {

    String removePatient(Patient patient);

    // String acceptPatient(Patient patient);

    // String rejectPatient(Patient patient);

    String addVaccinationCenter(VaccinationCenter vaccinationCenter);

    String addAdmin(Admin admin);

    // String deleteVaccinationCenter(VaccinationCenter vaccinationCenter);

    // String updateVaccinationCenter(VaccinationCenter vaccinationCenter);

    // String listVaccinationCenter();

    // Vaccine createVaccine(Vaccine vaccine);

    // String deleteVaccine(Vaccine vaccine);

    // String updateVaccine(Vaccine vaccine);

    // String listVaccine();

}
