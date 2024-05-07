package com.clinic.vaxschedular.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "VaccineCenter_Vaccine")
public class VaccineCenter_Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Vaccine_Center_id")
    private int vaccineCenterId;

    @Column(name = "Vaccine_id")
    private int vaccineId;

    public VaccineCenter_Vaccine(int vaccineCenterId, int vaccineId) {
        this.vaccineCenterId = vaccineCenterId;
        this.vaccineId = vaccineId;
    }

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Vaccine_Center_id", referencedColumnName = "Vaccine_Center_id", insertable = false, updatable = false)
    private VaccinationCenter vaccinationCenter;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "Vaccine_id", referencedColumnName = "Vaccine_id", insertable = false, updatable = false)
    private Vaccine vaccine;

}
