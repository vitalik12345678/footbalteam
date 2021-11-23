package com.task.footbalteam.service.impl;

import com.task.footbalteam.DTO.DtoConverter;
import com.task.footbalteam.DTO.players.PlayerCreateProfile;
import com.task.footbalteam.DTO.players.PlayerUpdateProfile;
import com.task.footbalteam.exception.ExistException;
import com.task.footbalteam.exception.NotExistException;
import com.task.footbalteam.model.Players;
import com.task.footbalteam.model.Team;
import com.task.footbalteam.repository.PlayersRepository;
import com.task.footbalteam.repository.TeamRepository;
import com.task.footbalteam.service.PlayersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PlayersServiceImpl implements PlayersService {

    private final PlayersRepository playersRepository;
    private final TeamRepository teamRepository;
    private final DtoConverter dtoConverter;

    @Autowired
    public PlayersServiceImpl(PlayersRepository playersRepository, TeamRepository teamRepository, DtoConverter dtoConverter) {
        this.playersRepository = playersRepository;
        this.teamRepository = teamRepository;
        this.dtoConverter = dtoConverter;
    }


    @Override
    public ResponseEntity<List<Players>> getAllUsers() {
        List<Players> players = playersRepository.findAll();
        return ResponseEntity.ok(players);
    }

    /**
     * Use this endpoint for find player
     *
     * @param id player's id
     * @return
     */
    @Override
    public ResponseEntity<Players> findPlayer(Long id) {
        Optional<Players> optionalPlayers = playersRepository.findById(id);
        if (optionalPlayers.isPresent()) {
            return ResponseEntity.ok(optionalPlayers.get());
        } else {
            log.error("Player wasnt found");
            throw new NotExistException("Player not exist ");
        }
    }

    @Override
    public ResponseEntity<?> deletePlayer(Long id) {
        Optional<Players> optionalPlayers = playersRepository.findById(id);
        if (optionalPlayers.isPresent()) {
            playersRepository.delete(optionalPlayers.get());
            return ResponseEntity.ok("Player was deleted successful");
        } else {
            log.error("Player wasnt found");
            throw new NotExistException("Player not exist");
        }
    }

    @Override
    public ResponseEntity<String> updatePlayer(Long id, PlayerUpdateProfile playerUpdateProfile) {
        Optional<Players> optionalPlayers = playersRepository.findById(id);
        if (optionalPlayers.isPresent()) {
            Players players = optionalPlayers.get();
            BeanUtils.copyProperties(playerUpdateProfile, players);
            playersRepository.save(players);
            return ResponseEntity.ok("Player was deleted successful");
        } else {
            log.error("Player wasnt found");
            throw new NotExistException("Player not exist");
        }
    }

    @Override
    public ResponseEntity<String> createPlayer(PlayerCreateProfile playerCreateProfile) {
        Optional<Players> optionalPlayers = playersRepository.findByCareerStartAndLastNameAndFirstName(playerCreateProfile.getCareerStart(), playerCreateProfile.getLastName(), playerCreateProfile.getFirstName());
        Optional<Team> optionalTeam = teamRepository.findById(playerCreateProfile.getTeamId());

        if (!optionalTeam.isPresent()){
            log.error("Team was not found/exist with id {} "+playerCreateProfile.getTeamId());
            throw new NotExistException("Team not exist");
        }

        if (optionalPlayers.isPresent()) {
            log.error("Player is already exist with id {}" + optionalPlayers.get().getId());
            throw new ExistException("Player exist");
        } else {
            Players players = new Players();
            players.setFirstName(playerCreateProfile.getFirstName());
            players.setCareerStart(playerCreateProfile.getCareerStart());
            players.setLastName(playerCreateProfile.getLastName());
            players.setTeam(optionalTeam.get());
            playersRepository.save(players);
            return ResponseEntity.ok("Player created successful");
        }
    }
}