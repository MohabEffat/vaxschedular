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

    private final String Role = "PATIENT";
    private int id;
    private long Ssn;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String vaccinationCenterName;
    private String vaccineName;
    private String Certification;

    public String getRole() {
        return Role;
    }

}
