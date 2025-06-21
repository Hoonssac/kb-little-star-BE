package com.kbstar.littlestar.user.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kbstar.littlestar.user.domain.User;

@Mapper
public interface UserMapper {
	User findByUserName(@Param("username") String username);
	void save(User user);
	void updateMainPokemon(@Param("userId") Long userId, @Param("pokemonId") Long pokemonId);
}
