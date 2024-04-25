package com.clinic.vaxschedular.Services;

import com.clinic.vaxschedular.Entity.Vaccine;
import org.springframework.stereotype.Service;

import com.clinic.vaxschedular.Entity.Admin;
import com.clinic.vaxschedular.Entity.Patient;
import com.clinic.vaxschedular.Entity.VaccinationCenter;

import java.util.List;
import java.util.Optional;
// import com.clinic.vaxschedular.Entity.Vaccine;

@Service
public interface AdminService {

    String removePatient(Patient patient);

    String addAdmin(Admin admin);
    // String acceptPatient(Patient patient);

    // String rejectPatient(Patient patient);

    String addVaccinationCenter(VaccinationCenter vaccinationCenter);

    boolean deleteVaccinationCenter(int id);

    boolean updateVaccinationCenter(VaccinationCenter vaccinationCenter);

    List<VaccinationCenter> listVaccinationCenter();

    boolean createVaccine(Vaccine vaccine);

    List<Vaccine> listVaccine();

    boolean deleteVaccine(int id);

    boolean updateVaccine(Vaccine vaccine);

}
