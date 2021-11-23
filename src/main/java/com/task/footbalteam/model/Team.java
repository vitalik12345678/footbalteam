package com.task.footbalteam.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.task.footbalteam.DTO.Convetible;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "team")
@Data
public class Team implements Convetible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private Integer score;

    @Column
    private Float fine;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "team")
    @JsonManagedReference
    private List<Players> players;
}
