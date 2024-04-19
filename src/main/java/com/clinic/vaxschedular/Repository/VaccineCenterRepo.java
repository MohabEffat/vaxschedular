package com.clinic.vaxschedular.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.clinic.vaxschedular.Entity.VaccinationCenter;

@Repository
@EnableJpaRepositories
public interface VaccineCenterRepo extends JpaRepository<VaccinationCenter, Integer> {

}
