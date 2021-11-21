package com.task.footbalteam.service.impl;

import com.task.footbalteam.model.Team;
import com.task.footbalteam.repository.PlayersRepository;
import com.task.footbalteam.repository.TeamRepository;
import com.task.footbalteam.service.PlayersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PlayersServiceImpl implements PlayersService {

    private final PlayersRepository playersRepository;
    private final TeamRepository teamRepository;

    @Autowired
    public PlayersServiceImpl(PlayersRepository playersRepository, TeamRepository teamRepository) {
        this.playersRepository = playersRepository;
        this.teamRepository = teamRepository;
    }


}
