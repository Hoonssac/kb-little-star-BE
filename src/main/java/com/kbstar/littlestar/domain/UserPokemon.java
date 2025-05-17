package com.kbstar.littlestar.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_pokemon")
@Getter
@Setter
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

    // 생성자

    public UserPokemon(User user, Pokemon pokemon) {
        this.user = user;
        this.pokemon = pokemon;
    }
}
