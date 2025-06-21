package com.kbstar.littlestar.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kbstar.littlestar.user.domain.UserPokemon;

@Mapper
public interface UserPokemonMapper {
	void save(UserPokemon userPokemon);

	List<UserPokemon> findByUserId(Long userId);
}
