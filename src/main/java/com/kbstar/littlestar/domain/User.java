package com.kbstar.littlestar.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
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

    private int mileage;

    private int ticketCount;

    private Long mainPokemonId;

    private LocalDate lastAnsweredDate;

    @ElementCollection
    @CollectionTable(name = "user_pokemon_ids", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "pokemon_id")
    private List<Long> pokemonIds = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "user_question_ids", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "question_id")
    private List<Long> questionIds = new ArrayList<>();
}

