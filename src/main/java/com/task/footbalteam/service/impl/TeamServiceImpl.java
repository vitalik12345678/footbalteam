package com.task.footbalteam.service.impl;

import com.task.footbalteam.DTO.DtoConverter;
import com.task.footbalteam.DTO.team.TeamCreateProfile;
import com.task.footbalteam.DTO.team.TeamUpdateProfile;
import com.task.footbalteam.DTO.transfer.TransferProfile;
import com.task.footbalteam.exception.*;
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
     * The method returns get team
     *
     *
     * @return {@link List<Team>}
     */

    @Override
    public ResponseEntity<List<Team>> getAllTeam() {
        List<Team> teamList = teamRepository.findAll();
        return ResponseEntity.ok(teamList);
    }

    /**
     * The method returns team by id
     *
     * @param id team id
     * @return {@link Team}
     * @throws {@link NotExistException}
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
     * The method returns created team
     *
     * @param teamCreateProfile - dto {@link TeamCreateProfile}
     * @return {@link Team}
     * @throws {@link ExistException}
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
     * The method returns delete team
     *
     * @param id team id
     * @throws {@link NotExistException}
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
     * @throws NotExistException
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

    /**
     * Use this method for transfer player from one team to another
     * Player's price = 10000 * EXPERIENCE_IN_MONTH /PLAYER_AGE
     *
     * @param transferProfile - object of DTO class {@link TransferProfile}
     * @return transfer player
     * @throws {@link NotExistException} or {@link ExistException}
     */

    @Override
    public ResponseEntity<?> transfer(TransferProfile transferProfile) {
        Optional<Team> optionalOldTeam = teamRepository.findById(transferProfile.getOldTeamId());
        Optional<Team> optionalNewTeam = teamRepository.findById(transferProfile.getNewTeamId());
        Optional<Players> optionalPlayer = playersRepository.findById(transferProfile.getPlayerId());

        if (optionalPlayer.isEmpty()){
            log.error(transferProfile.getPlayerId() + " player not exist");
            throw new NotExistException("Player not exist");
        }
        else if (optionalNewTeam.isEmpty() || optionalOldTeam.isEmpty() ){
            log.error("Incorrect team ");
            throw new NotExistException("Team/s not exist");
        }else if (optionalOldTeam.get().getId().equals(optionalNewTeam.get().getId())){
            log.error("Team have equal id");
            throw new NotCorrectDataException("Team have equal id");
        }
        else {
            Players players = optionalPlayer.get();
            Team oldTeam = optionalOldTeam.get();
            Team newTeam = optionalNewTeam.get();

            newTeam.getPlayers().stream().forEach(x ->{
                if (x.equals(players)){
                    log.error(x.getId()+" player's id in team");
                    throw new NotExistException("Player exist in new team");
                }
            });

            LocalDate localDate = LocalDate.now();

            long playerAge = Math.abs(ChronoUnit.YEARS.between(localDate,players.getCareerStart()));
            long mouth = ChronoUnit.MONTHS.between(players.getCareerStart(),localDate);
            int price = (int) ((100000 * Math.abs(mouth))/playerAge);
            price += price*oldTeam.getScore();

            if (newTeam.getScore() <= price){
             return new ResponseEntity<>("The new team does not have enough money",HttpStatus.FORBIDDEN);
            }else {
                players.setTeam(newTeam);
                oldTeam.setScore(oldTeam.getScore()+price);
                newTeam.setScore(newTeam.getScore()-price);
                playersRepository.save(players);
                teamRepository.save(oldTeam);
                teamRepository.save(newTeam);
                return ResponseEntity.ok(players);
            }
        }
    }
}
