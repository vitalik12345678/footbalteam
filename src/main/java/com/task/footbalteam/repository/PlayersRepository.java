package com.task.footbalteam.repository;

import com.task.footbalteam.model.Players;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface PlayersRepository extends JpaRepository<Players,Long> {

    @Override
    Optional<Players> findById(Long id);

    Optional<Players> findByCareerStartAndLastNameAndFirstName(LocalDate careerStart, String lastName, String firstName);
}
