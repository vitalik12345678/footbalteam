package com.task.footbalteam.controller;

import com.task.footbalteam.DTO.players.PlayerCreateProfile;
import com.task.footbalteam.DTO.players.PlayerUpdateProfile;
import com.task.footbalteam.model.Players;
import com.task.footbalteam.model.Team;
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

    @Autowired
    public PlayerController(PlayersService playersService) {
        this.playersService = playersService;
    }

    /**
     * Use this endpoint for get all player
     *
     * @return {@link List< Players >}
     */

    @GetMapping(value = "/v1/allPlayers")
    public ResponseEntity<List<Players>> getAllUsers(){
        return playersService.getAllUsers();
    }

    /**
     * Use this endpoint for get player
     *
     * @return {@link Players}
     */

    @GetMapping(value = "/v1/{id}")
    public ResponseEntity<Players> findPlayer(@PathVariable("id")Long id){
        return playersService.findPlayer(id);
    }

    /**
     * Use this endpoint for create player
     *
     * @return {@link Players}
     */

    @PostMapping(value = "/v1/")
    public ResponseEntity<Players> createPlayer(@Valid @RequestBody PlayerCreateProfile playerCreateProfile){
        return playersService.createPlayer(playerCreateProfile);
    }
    /**
     * Use this endpoint for update player
     *
     * @return {@link Players}
     */

    @PutMapping(value = "/v1/{id}")
    public ResponseEntity<Players> updatePlayer(@PathVariable("id") Long id,
                                                @Valid
                                                @RequestBody
                                                PlayerUpdateProfile playerUpdateProfile){
        return playersService.updatePlayer(id,playerUpdateProfile);
    }
    /**
     * Use this endpoint for delete player
     *
     * @return {@link Players}
     */

    @DeleteMapping(value = "/v1/{id}")
    public ResponseEntity<?> deletePlayer(@PathVariable("id") Long id){
        return playersService.deletePlayer(id);
    }

}
