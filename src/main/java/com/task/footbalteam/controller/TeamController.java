package com.task.footbalteam.controller;

import com.task.footbalteam.DTO.team.TeamCreateProfile;
import com.task.footbalteam.DTO.team.TeamUpdateProfile;
import com.task.footbalteam.DTO.transfer.TransferProfile;
import com.task.footbalteam.model.Team;
import com.task.footbalteam.service.PlayersService;
import com.task.footbalteam.service.TeamService;
import com.task.footbalteam.service.impl.PlayersServiceImpl;
import com.task.footbalteam.service.impl.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/team")
public class TeamController {


    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    /**
     * Use this endpoint for get all team
     *
     * @return {@link List<Team>}
     */

    @GetMapping(value = "/v1/allTeam")
    public ResponseEntity<List<Team>> getAllTeam(){
        return teamService.getAllTeam();
    }

    /**
     * Use this endpoint for get one team
     *
     * @param id team's id
     * @return {@link Team}
     */

    @GetMapping(value = "/v1/{id}")
    public ResponseEntity<Team> getTeam(@PathVariable("id") Long id){
        return teamService.findTeam(id);
    }

    /**
     * Use this endpoint fro create team
     *
     * @param teamCreateProfile object of DTO class {@link TeamCreateProfile}
     * @return {@link Team}
     */

    @PostMapping(value = "/v1/")
    public ResponseEntity<Team> createTeam(
            @Valid
            @RequestBody TeamCreateProfile teamCreateProfile){
        return teamService.createTeam(teamCreateProfile);
    }

    /**
     * Use this endpoint for update some info about team
     *
     * @param id team's id
     * @param teamUpdateProfile object of DTO class {@link TeamUpdateProfile}
     * @return {@link Team}
     */

    @PutMapping(value = "/v1/{id}")
    public ResponseEntity<Team> updateTeam(
            @PathVariable("id") Long id,
            @Valid
            @RequestBody TeamUpdateProfile teamUpdateProfile){
        return teamService.updateTeam(id,teamUpdateProfile);
    }

    /**
     *
     * Use this endpoint for delete team
     *
     * @param id team's id
     * @return {@link Team}
     */

    @DeleteMapping("/v1/{id}")
    public ResponseEntity<?> deleteTeam(@PathVariable("id") Long id){
        return teamService.deleteTeam(id);
    }

    /**
     * Use this endpoint for transfer player from one team to another
     *
     * @param transferProfile object of DTO class {@link TransferProfile}
     * @return
     */
    @PutMapping(value = "/v1/transfer")
    public ResponseEntity<?> playerTransfer(@Valid
                                 @RequestBody TransferProfile transferProfile){
        return teamService.transfer(transferProfile);
    }
}
