package com.clinic.vaxschedular.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.vaxschedular.Entity.Patient_Vaccine;

public interface Patient_Vaccine_Repo extends JpaRepository<Patient_Vaccine, Integer> {

    Optional<Patient_Vaccine> findByVaccidAndPatid(int vaccid, int patid);

}
