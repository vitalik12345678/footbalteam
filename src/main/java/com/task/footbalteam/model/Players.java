package com.task.footbalteam.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.task.footbalteam.DTO.Convetible;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "players")
@Data
public class Players implements Convetible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "carier_start")
    private LocalDate careerStart;

    @ManyToOne()
    @JoinColumn(name = "team_id")
    @JsonBackReference
    private Team team;

}
