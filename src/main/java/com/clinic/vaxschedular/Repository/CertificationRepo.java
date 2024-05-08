package com.clinic.vaxschedular.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clinic.vaxschedular.Entity.Certification;

public interface CertificationRepo extends JpaRepository<Certification, Integer> {

}
