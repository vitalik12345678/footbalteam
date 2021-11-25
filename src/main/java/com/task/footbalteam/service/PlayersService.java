package com.task.footbalteam.service;

import com.task.footbalteam.DTO.players.PlayerCreateProfile;
import com.task.footbalteam.DTO.players.PlayerUpdateProfile;
import com.task.footbalteam.model.Players;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlayersService {



    ResponseEntity<List<Players>> getAllUsers();

    ResponseEntity<Players> findPlayer(Long id);

    ResponseEntity<?> deletePlayer(Long id);

    ResponseEntity<Players> updatePlayer(Long id, PlayerUpdateProfile playerUpdateProfile);

    ResponseEntity<Players> createPlayer(PlayerCreateProfile playerCreateProfile);

}
