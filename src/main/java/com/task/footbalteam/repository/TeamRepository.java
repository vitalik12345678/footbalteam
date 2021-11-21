package com.task.footbalteam.repository;

import com.task.footbalteam.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {

    Optional<Team> findById(Long id);

}
