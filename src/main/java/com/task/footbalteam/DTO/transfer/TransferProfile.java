package com.task.footbalteam.DTO.transfer;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TransferProfile {

    @NotNull
    private Long playerId;

    @NotNull
    private Long oldTeamId;

    @NotNull
    private Long newTeamId;

}
