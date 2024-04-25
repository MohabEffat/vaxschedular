package com.clinic.vaxschedular.Entity;

import com.clinic.vaxschedular.Config.AesEncryptor;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "Patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Convert(converter = AesEncryptor.class)
    @Column(name = "Ssn", nullable = false, unique = true)
    private long Ssn;

    @Convert(converter = AesEncryptor.class)
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Convert(converter = AesEncryptor.class)
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Convert(converter = AesEncryptor.class)
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Convert(converter = AesEncryptor.class)
    @Column(name = "vaccine_Name", nullable = false)
    private String vaccineName;

    @Convert(converter = AesEncryptor.class)
    @Column(name = "certification", nullable = true)
    private String certification;

    @Convert(converter = AesEncryptor.class)
    @ManyToOne
    @JoinColumn(name = "vaccination_Center", referencedColumnName = "Center_name", insertable = false, updatable = false)
    private VaccinationCenter vaccinationCenter;
}
