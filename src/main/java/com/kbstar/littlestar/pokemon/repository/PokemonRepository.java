package com.kbstar.littlestar.pokemon.repository;

import com.kbstar.littlestar.pokemon.domain.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}

