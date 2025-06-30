package com.kbstar.littlestar.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kbstar.littlestar.user.domain.UserPokemon;

@Mapper
public interface UserPokemonMapper {
	void updateMainPokemon(@Param("userId") Long userId, @Param("pokemonId") Long pokemonId);
	void save(UserPokemon userPokemon);
	List<UserPokemon> findByUserId(Long userId);
}
