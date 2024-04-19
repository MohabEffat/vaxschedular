package com.clinic.vaxschedular.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {

    private final String Role = "ADMIN";
    private int id;
    private long Ssn;
    private String email;
    private String password;
    private StrictMath VaccineCenter;

    public String getRole() {
        return Role;
    }
}
