package com.kbstar.littlestar.repository;

import com.kbstar.littlestar.domain.UserPokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPokemonRepository extends JpaRepository<UserPokemon, Long> {
}
