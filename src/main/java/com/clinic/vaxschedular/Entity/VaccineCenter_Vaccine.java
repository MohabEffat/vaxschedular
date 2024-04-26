package com.clinic.vaxschedular.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "VaccineCenter_Vaccine")
public class VaccineCenter_Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "Vaccine_Center")
    private VaccinationCenter vaccinationCenter;

    @ManyToOne
    @JoinColumn(name = "vaccine_Name")
    private Vaccine vaccine;

}
