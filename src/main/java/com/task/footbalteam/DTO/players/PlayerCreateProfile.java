package com.task.footbalteam.DTO.players;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class PlayerCreateProfile {

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]*$")
    private String firstName;

    @NotEmpty
    @Pattern(regexp = "^[A-Za-z]*$")
    private String lastName;

    @NotNull
    private LocalDate careerStart;

    @NotNull
    private Long teamId;

}
