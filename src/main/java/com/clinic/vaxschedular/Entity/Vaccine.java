package com.clinic.vaxschedular.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @Column(name = "vaccine_Name", nullable = false)
    private String vaccineName;

    @Column(name = "duration_Between_Doses", nullable = false)
    private String durationBetweenDoses;

    @Column(name = "precautions", nullable = false)
    private String precautions;

    @Column(name = "vaccination_Center_Name", nullable = false)
    private String vaccinationCenterName;

}