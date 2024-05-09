package com.clinic.vaxschedular.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @Email
    @NotNull(message = "Invalid Password OR Email")
    private String email;

    @NotNull(message = "Invalid Password OR Email")
    private String password;
}