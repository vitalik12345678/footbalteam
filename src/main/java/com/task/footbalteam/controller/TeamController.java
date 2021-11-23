package com.task.footbalteam.controller;

import com.task.footbalteam.DTO.team.TeamCreateProfile;
import com.task.footbalteam.DTO.team.TeamUpdateProfile;
import com.task.footbalteam.model.Team;
import com.task.footbalteam.service.impl.PlayersServiceImpl;
import com.task.footbalteam.service.impl.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/team")
public class TeamController {

    private final PlayersServiceImpl playersService;
    private final TeamServiceImpl teamService;

    @Autowired
    public TeamController(PlayersServiceImpl playersService, TeamServiceImpl teamService) {
        this.playersService = playersService;
        this.teamService = teamService;
    }

    @GetMapping(value = "/v1/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable("id") Long id){
        return teamService.findTeam(id);
    }

    @PostMapping(value = "/v1/")
    public ResponseEntity<String> createTeam(
            @Valid
            @RequestBody TeamCreateProfile teamCreateProfile){
        return teamService.createTeam(teamCreateProfile);
    }

    @PutMapping(value = "/v1/{id}")
    public ResponseEntity<String> updateTeam(
            @PathVariable("id") Long id,
            @Valid
            @RequestBody TeamUpdateProfile teamUpdateProfile){
        return teamService.updateTeam(id,teamUpdateProfile);
    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable("id") Long id){
        return teamService.deleteTeam(id);
    }
}
