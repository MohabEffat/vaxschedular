package com.clinic.vaxschedular.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Certification")
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Certificate_id")
    private int id;

    @Column(name = "Patient_id", nullable = false)
    private int patientId;

    @Column(name = "vaccine_name", nullable = false)
    private int vaccineId;

    @Column(name = "vaccinationCenter_name", nullable = false)
    private int vaccinationCenterId;

    @ManyToOne
    @JoinColumn(name = "Patient_id", referencedColumnName = "Patient_id", insertable = false, updatable = false)
    private Patient patient;
}
