package com.clinic.vaxschedular.DTO;

import com.clinic.vaxschedular.Entity.Patient;

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
@Table(name = "Certificate")
public class Certification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Certificate_id")
    private int id;

    @Column(name = "Patient_id", nullable = false)
    private String patientName;

    @Column(name = "vaccine_name", nullable = false)
    private String vaccineName;

    @Column(name = "vaccinationCenter_name", nullable = false)
    private String vaccinationCenterName;

    @ManyToOne
    @JoinColumn(name = "Patient_id", referencedColumnName = "Patient_id", insertable = false, updatable = false)
    private Patient patient;
}
