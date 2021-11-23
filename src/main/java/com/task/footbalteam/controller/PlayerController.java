package com.task.footbalteam.controller;

import com.task.footbalteam.DTO.players.PlayerCreateProfile;
import com.task.footbalteam.DTO.players.PlayerUpdateProfile;
import com.task.footbalteam.model.Players;
import com.task.footbalteam.service.PlayersService;
import com.task.footbalteam.service.TeamService;
import com.task.footbalteam.service.impl.PlayersServiceImpl;
import com.task.footbalteam.service.impl.TeamServiceImpl;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {


    private final PlayersService playersService;
    private final TeamService teamService;

    @Autowired
    public PlayerController(PlayersService playersService, TeamService teamService) {
        this.playersService = playersService;
        this.teamService = teamService;
    }

    @GetMapping(value = "/v1/allPlayers")
    public ResponseEntity<List<Players>> getAllUsers(){
        return playersService.getAllUsers();
    }

    @GetMapping(value = "/v1/{id}")
    public ResponseEntity<Players> findPlayer(@PathVariable("id")Long id){
        return playersService.findPlayer(id);
    }

    @PostMapping(value = "/v1/")
    public ResponseEntity<String> createPlayer(@Valid @RequestBody PlayerCreateProfile playerCreateProfile){
        return playersService.createPlayer(playerCreateProfile);
    }

    @PutMapping(value = "/v1/{id}")
    public ResponseEntity<String > updatePlayer(@PathVariable("id") Long id,
                                                @Valid
                                                @RequestBody
                                                PlayerUpdateProfile playerUpdateProfile){
        return playersService.updatePlayer(id,playerUpdateProfile);
    }

    @DeleteMapping(value = "/v1/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable("id") Long id){
        return playersService.deletePlayer(id);
    }

}
