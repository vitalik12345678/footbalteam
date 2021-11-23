package com.task.footbalteam.DTO.team;

import com.task.footbalteam.DTO.Convetible;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class TeamUpdateProfile implements Convetible {

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]*$",message = "Use only english letters")
    private String name;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]*$",message = "Use only english letters")
    private String city;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]*$",message = "Use only english letters")
    private String country;

}
