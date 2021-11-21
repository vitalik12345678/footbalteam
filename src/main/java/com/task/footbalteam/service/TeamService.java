package com.task.footbalteam.service;

import com.task.footbalteam.model.Team;
import org.springframework.http.ResponseEntity;

public interface TeamService {

    ResponseEntity<Team> findTeam(Long id);

}
