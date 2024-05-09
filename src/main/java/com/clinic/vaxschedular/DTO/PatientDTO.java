package com.clinic.vaxschedular.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    @NotNull(message = "Invalid Password OR Email")
    private int Patient_Id;
    @Email
    @Min(11)
    @Max(50)
    @NotNull
    private String Patient_Email;
    @Min(5)
    @Max(20)
    @NotNull(message = "Invalid Password OR Email")
    private String Password;
    @NotNull
    private String Vaccination_Center;
}