package com.clinic.vaxschedular.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CenterDTO {

    private final String Role = "CENTER";
    private int id;
    private String email;
    private String password;
    private String centerName;
    private int adminId;

    public String getRole() {
        return Role;
    }
}
