package com.task.footbalteam.DTO.team;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class TeamCreateProfile {

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]*$",message = "Use only english letters")
    private String name;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]*$",message = "Use only english letters")
    private String city;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]*$",message = "Use only english letters")
    private String country;

    @NotEmpty
    private Integer score;

    @NotEmpty
    private Float fine;

}
