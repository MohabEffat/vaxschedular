package com.clinic.vaxschedular.Services;

import com.clinic.vaxschedular.Entity.Vaccine;
import org.springframework.stereotype.Service;

import com.clinic.vaxschedular.DTO.PatientDTO;
import com.clinic.vaxschedular.Entity.Admin;
import com.clinic.vaxschedular.Entity.Certification;
import com.clinic.vaxschedular.Entity.VaccinationCenter;

import java.util.List;

@Service
public interface AdminService {

    String removePatient(int id);

    String addAdmin(Admin admin);

    String addVaccinationCenter(VaccinationCenter vaccinationCenter);

    String deleteVaccinationCenter(int id);

    String updateVaccinationCenter(int id, VaccinationCenter vaccinationCenter);

    String updateVaccinationCenter(int Center_id, int Vaccine_id);

    List<VaccinationCenter> listVaccinationCenter(int id);

    String createVaccine(Vaccine vaccine);

    List<Vaccine> listVaccine();

    String deleteVaccine(int id);

    String updateVaccine(int id, Vaccine vaccine);

    List<PatientDTO> listPatients();

    String uploadCertificate(Certification certificate);
}
