package com.task.footbalteam.service.impl;

import com.task.footbalteam.DTO.team.TeamCreateProfile;
import com.task.footbalteam.DTO.team.TeamUpdateProfile;
import com.task.footbalteam.exception.ExistException;
import com.task.footbalteam.exception.NotExistException;
import com.task.footbalteam.model.Team;
import com.task.footbalteam.repository.PlayersRepository;
import com.task.footbalteam.repository.TeamRepository;
import com.task.footbalteam.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Optional;

@Service
@Slf4j
public class TeamServiceImpl implements TeamService {

    private final PlayersRepository playersRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(PlayersRepository playersRepository, TeamRepository teamRepository) {
        this.playersRepository = playersRepository;
        this.teamRepository = teamRepository;
    }

    /**
     * Use this endpoint for get team by id
     *
     * @param id team id
     * @return status 200
     */
    @Override
    public ResponseEntity<Team> findTeam(Long id) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isPresent()) {
            Team team = optionalTeam.get();
            log.info("Team id {} " + team.getId());
            return ResponseEntity.ok(team);
        } else {
            throw  new NotExistException("");
        }
    }

    @Override
    public ResponseEntity<String> createTeam(TeamCreateProfile teamCreateProfile) {

        Optional<Team> optionalTeam = teamRepository.findByName(teamCreateProfile.getName());
        if (optionalTeam.isPresent()){
            throw new ExistException("The team is incorrectly name"+ teamCreateProfile.getName());
        }
        else{
        Team team = new Team();
        team.setCity(teamCreateProfile.getCity());
        team.setCountry(teamCreateProfile.getCountry());
        team.setName(teamCreateProfile.getName());
        team.setFine(teamCreateProfile.getFine());
        team.setScore(teamCreateProfile.getScore());
        teamRepository.save(team);
        log.info("Created successful");
        return ResponseEntity.created(URI.create("/team/"+teamCreateProfile.getName())).build();
        }
    }

    @Override
    public ResponseEntity<?> deleteTeam(Long id) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isPresent()){
            log.info("Team deleted is id"+id);
            teamRepository.deleteById(id);
            return new ResponseEntity<>(id,HttpStatus.OK);
        }else {
            throw new NotExistException("The team does not exist");
        }
    }

    @Override
    public ResponseEntity<String> updateTeam(Long id, TeamUpdateProfile teamUpdateProfile) {

    }
}
