package com.clinic.vaxschedular.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.vaxschedular.Entity.VaccineCenter_Vaccine;

public interface VaccineCenter_Vaccine_Repo extends JpaRepository<VaccineCenter_Vaccine, Integer> {

}
