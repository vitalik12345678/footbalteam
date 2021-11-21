package com.task.footbalteam.controller;

import com.task.footbalteam.model.Team;
import com.task.footbalteam.service.impl.PlayersServiceImpl;
import com.task.footbalteam.service.impl.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/")
    public ResponseEntity<Team> getTeam(){
        return teamService.findTeam(2L);
    }
}
