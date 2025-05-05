package com.kbstar.littlestar.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
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
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private LocalDate lastAnsweredDate;

    private Integer age;


    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> pokemonIds;

    private Integer mainPokemonId;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Integer> questionIds;

    private Integer mileage;

    private Integer ticketCount = 0;
}

