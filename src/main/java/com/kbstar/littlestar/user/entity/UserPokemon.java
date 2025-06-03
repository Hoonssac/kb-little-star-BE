package com.kbstar.littlestar.user.entity;

import com.kbstar.littlestar.pokemon.domain.Pokemon;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_pokemon")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserPokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 어떤 유저가

    @ManyToOne
    @JoinColumn(name = "pokemon_id")
    private Pokemon pokemon; // 어떤 포켓몬을 보유

    @Builder
    public UserPokemon(User user, Pokemon pokemon) {
        this.user = user;
        this.pokemon = pokemon;
    }
}
