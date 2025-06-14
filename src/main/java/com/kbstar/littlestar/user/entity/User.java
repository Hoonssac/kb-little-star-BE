package com.kbstar.littlestar.user.entity;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kbstar.littlestar.pokemon.domain.Pokemon;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private LocalDate lastAnsweredDate;

    private Integer age;

    private Integer mileage;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserPokemon> userPokemons = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "main_pokemon_id")
    private Pokemon mainPokemon;

    @Builder
    public User(String username, String password, Integer age, Integer mileage, LocalDate lastAnsweredDate,
        Pokemon mainPokemon) {
        this.username = username;
        this.password = password;
        this.age = age;
        this.mileage = mileage;
        this.lastAnsweredDate = lastAnsweredDate;
        this.mainPokemon = mainPokemon;
    }

    public void addPokemon(Pokemon pokemon) {
        UserPokemon userPokemon = UserPokemon.builder()
            .user(this)
            .pokemon(pokemon)
            .build();
        this.userPokemons.add(userPokemon);
    }

    public void changeMainPokemon(Pokemon mainPokemon) {
        this.mainPokemon = mainPokemon;
    }

    public void updateMileage(int mileage) {
        this.mileage = mileage;
    }

    public void updateLastAnsweredDate(LocalDate date) {
        this.lastAnsweredDate = date;
    }
}

