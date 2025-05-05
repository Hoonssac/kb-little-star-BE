package com.kbstar.littlestar.repository;

import com.kbstar.littlestar.domain.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
}

