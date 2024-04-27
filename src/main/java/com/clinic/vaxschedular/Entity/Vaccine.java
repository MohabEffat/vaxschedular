package com.clinic.vaxschedular.Entity;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Vaccine")
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "vaccine_Name", nullable = false, unique = true)
    private String vaccineName;

    @Column(name = "duration_Between_Doses", nullable = false)
    private String durationBetweenDoses;

    @Column(name = "precautions", nullable = false)
    private String precautions;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "Patient_Vaccine", joinColumns = @JoinColumn(name = "vaccine_Name"), inverseJoinColumns = @JoinColumn(name = "Patient_email"))
    private Set<Patient> patients = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "VaccineCenter_Vaccine", joinColumns = @JoinColumn(name = "vaccine_Name"), inverseJoinColumns = @JoinColumn(name = "Vaccine_Center"))
    private Set<VaccinationCenter> vaccinationCenters = new HashSet<>();

}
