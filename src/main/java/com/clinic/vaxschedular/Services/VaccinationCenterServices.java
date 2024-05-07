package com.clinic.vaxschedular.Services;

import org.springframework.stereotype.Service;

import com.clinic.vaxschedular.DTO.Certification;
import com.clinic.vaxschedular.Entity.Patient;

@Service
public interface VaccinationCenterServices {

    String viewPatients();

    String approveDoses(Patient patient);

    String uploadCertification(Certification certification);

    String preventPatientWhoTakeVaccineBefore(Patient patient);

}
