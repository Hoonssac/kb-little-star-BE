package com.kbstar.littlestar.user.domain;

import com.kbstar.littlestar.pokemon.domain.Pokemon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPokemon {
    private Long id;
    private User user;
    private Pokemon pokemon;
}
