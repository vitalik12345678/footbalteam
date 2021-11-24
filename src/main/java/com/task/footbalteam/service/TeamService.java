package com.task.footbalteam.service;

import com.task.footbalteam.DTO.team.TeamCreateProfile;
import com.task.footbalteam.DTO.team.TeamUpdateProfile;
import com.task.footbalteam.DTO.transfer.TransferProfile;
import com.task.footbalteam.model.Team;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TeamService {

    ResponseEntity<List<Team>> getAllTeam();

    ResponseEntity<Team> findTeam(Long id);

    ResponseEntity<Team> createTeam(TeamCreateProfile teamCreateProfile);

    ResponseEntity<?> deleteTeam(Long id);

    ResponseEntity<Team> updateTeam(Long id, TeamUpdateProfile teamUpdateProfile);

    ResponseEntity<?> transfer(TransferProfile transferProfile);

}
