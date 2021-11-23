package com.task.footbalteam.DTO.players;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
public class PlayerUpdateProfile {

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]*$")
    private String firstName;

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]*$")
    private String lastName;

}
