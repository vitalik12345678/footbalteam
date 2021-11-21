package com.task.footbalteam.controller;

import com.task.footbalteam.model.Team;
import com.task.footbalteam.service.impl.PlayersServiceImpl;
import com.task.footbalteam.service.impl.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/v1/")
    public ResponseEntity<Team> getTeam(@RequestParam("id") Long id){
        return teamService.findTeam(id);
    }
}
