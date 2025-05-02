package com.kbstar.littlestar.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private LocalDate lastAnsweredDate;

    private Integer age;


    @ElementCollection
    private List<Integer> pokemonIds;

    private Integer mainPokemonId;

    @ElementCollection
    private List<Integer> questionIds;

    private Integer mileage;

    private Integer ticketCount = 0;
}

