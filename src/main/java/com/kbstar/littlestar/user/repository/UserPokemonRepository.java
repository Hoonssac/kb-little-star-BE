package com.kbstar.littlestar.user.repository;

import com.kbstar.littlestar.user.entity.UserPokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPokemonRepository extends JpaRepository<UserPokemon, Long> {
}
