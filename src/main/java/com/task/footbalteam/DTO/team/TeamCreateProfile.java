package com.task.footbalteam.DTO.team;

import com.task.footbalteam.DTO.Convetible;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class TeamCreateProfile implements Convetible {

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]*$",message = "Use only english letters")
    private String name;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]*$",message = "Use only english letters")
    private String city;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]*$",message = "Use only english letters")
    private String country;

    @NotNull
    private Integer score;

    @NotNull
    private Float fine;

}
