package com.task.footbalteam.service.impl;

import com.task.footbalteam.model.Team;
import com.task.footbalteam.repository.PlayersRepository;
import com.task.footbalteam.repository.TeamRepository;
import com.task.footbalteam.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    @Override
    public ResponseEntity<Team> findTeam(Long id) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isPresent()) {
            Team team = optionalTeam.get();
            log.info("Team id {} " + team.getId());
            return new ResponseEntity<Team>(team , HttpStatus.FOUND);
        } else {
            throw new RuntimeException("Not existt");
        }
    }
}
