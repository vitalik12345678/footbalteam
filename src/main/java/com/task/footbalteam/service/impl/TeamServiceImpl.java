package com.task.footbalteam.service.impl;

import com.task.footbalteam.DTO.DtoConverter;
import com.task.footbalteam.DTO.team.TeamCreateProfile;
import com.task.footbalteam.DTO.team.TeamUpdateProfile;
import com.task.footbalteam.DTO.transfer.TransferProfile;
import com.task.footbalteam.exception.ExistException;
import com.task.footbalteam.exception.NotExistException;
import com.task.footbalteam.model.Players;
import com.task.footbalteam.model.Team;
import com.task.footbalteam.repository.PlayersRepository;
import com.task.footbalteam.repository.TeamRepository;
import com.task.footbalteam.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class TeamServiceImpl implements TeamService {

    private final PlayersRepository playersRepository;
    private final TeamRepository teamRepository;
    private final DtoConverter dtoConverter;

    @Autowired
    public TeamServiceImpl(PlayersRepository playersRepository, TeamRepository teamRepository, DtoConverter dtoConverter) {
        this.playersRepository = playersRepository;
        this.teamRepository = teamRepository;
        this.dtoConverter = dtoConverter;
    }

    /**
     * Use this endpoin for get all team
     *
     *
     * @return status 200(team list)
     */

    @Override
    public ResponseEntity<List<Team>> getAllTeam() {
        List<Team> teamList = teamRepository.findAll();
        return ResponseEntity.ok(teamList);
    }

    /**
     * Use this endpoint for get team by id
     *
     * @param id team id
     * @return {@link Team} or throw {@link NotExistException}
     */
    @Override
    public ResponseEntity<Team> findTeam(Long id) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isPresent()) {
            Team team = optionalTeam.get();
            log.info("Team id {} " + team.getId());
            return ResponseEntity.ok(team);
        } else {
            throw new NotExistException("Team not exist");
        }
    }

    /**
     * Use this endpoint for create team
     *
     * @param teamCreateProfile - dto {@link TeamCreateProfile}
     * @return URI to created team or throw {@link ExistException}
     */

    @Override
    public ResponseEntity<Team> createTeam(TeamCreateProfile teamCreateProfile) {

        Optional<Team> optionalTeam = teamRepository.findByName(teamCreateProfile.getName());
        if (optionalTeam.isPresent()){
            log.info(String.format("team is exist with id {%s}",optionalTeam.get().getId()));
            throw new ExistException("The team is incorrectly name " + teamCreateProfile.getName());
        }
       else{
        Team team = dtoConverter.convertToEntity(teamCreateProfile,new Team());
        teamRepository.save(team);
        log.info("Created successful");
        return ResponseEntity.ok(team);
       }
    }

    /**
     * Use this endpoint for delete team
     *
     * @param id team id
     * @return status 200 or throw {@link NotExistException}
     */

    @Override
    public ResponseEntity<?> deleteTeam(Long id) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isPresent()){
            log.info("Team deleted is id"+id);
            teamRepository.deleteById(id);
            return new ResponseEntity<>("Team deleted successful",HttpStatus.OK);
        }else {
            throw new NotExistException("The team does not exist");
        }
    }

    /**
     * Use this endpoint for update some information about team
     *
     * @param id team id
     * @param teamUpdateProfile object of DTO class {@link TeamUpdateProfile}
     * @return status 200 or throw {@link NotExistException}
     */

    @Override
    public ResponseEntity<Team> updateTeam(Long id, TeamUpdateProfile teamUpdateProfile) {
        Optional<Team> optionalTeam = teamRepository.findById(id);
        if (optionalTeam.isPresent()){
            Team team = optionalTeam.get();
            BeanUtils.copyProperties(teamUpdateProfile,team);
            log.info("Team update id{}  "+team.getId());
            teamRepository.save(team);
            return ResponseEntity.ok(team);
        }else {
            throw new NotExistException("Team not exist ");
        }
    }

    @Override
    public ResponseEntity<Team> transfer(TransferProfile transferProfile) {
        Optional<Team> optionalOldTeam = teamRepository.findById(Long.valueOf(transferProfile.getOldTeamId()));
        Optional<Team> optionalNewTeam = teamRepository.findById(Long.valueOf(transferProfile.getNewTeamId()));
        Optional<Players> optionalPlayer = playersRepository.findById(Long.valueOf(transferProfile.getPlayerId()));

        if (optionalPlayer.isEmpty()){
            log.error(transferProfile.getPlayerId() + " player not exist");
            throw new NotExistException("Player not exist");
        }else if (optionalNewTeam.isEmpty() || optionalOldTeam.isEmpty()){
            log.error("Incorrect team ");
            throw new NotExistException("Team/s not exist");
        }else {
            Players players = optionalPlayer.get();
            Team oldTeam = optionalOldTeam.get();
            Team newTeam = optionalNewTeam.get();

            LocalDate localDate = LocalDate.now();

            long mouth = ChronoUnit.MONTHS.between(players.getCareerStart(),localDate);

            if (mouth <= 0){
                throw new RuntimeException("Mount < 0");
            }

            long salasry = (100000 * Math.abs(mouth))/20;

            System.out.println(salasry);

           // LocalDate experience = ( LocalDate.now(). players.getCareerStart() )
            return null;
        }
    }
}
