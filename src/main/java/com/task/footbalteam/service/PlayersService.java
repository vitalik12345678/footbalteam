package com.task.footbalteam.service;

import com.task.footbalteam.DTO.players.PlayerCreateProfile;
import com.task.footbalteam.DTO.players.PlayerUpdateProfile;
import com.task.footbalteam.model.Players;
import com.task.footbalteam.model.Team;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlayersService {

    ResponseEntity<List<Players>> getAllUsers();

    ResponseEntity<Players> findPlayer(Long id);

    ResponseEntity<?> deletePlayer(Long id);

    ResponseEntity<String> updatePlayer(Long id, PlayerUpdateProfile playerUpdateProfile);

    ResponseEntity<String> createPlayer(PlayerCreateProfile playerCreateProfile);

}
