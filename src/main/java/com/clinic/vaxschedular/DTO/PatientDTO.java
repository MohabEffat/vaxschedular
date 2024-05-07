package com.clinic.vaxschedular.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

    private int Patient_Id;
    private String Patient_Email;
    private String Password;
    private String Vaccination_Center;
}
