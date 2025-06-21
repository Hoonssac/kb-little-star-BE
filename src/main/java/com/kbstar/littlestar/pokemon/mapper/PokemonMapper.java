package com.kbstar.littlestar.pokemon.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kbstar.littlestar.pokemon.domain.Pokemon;

@Mapper
public interface PokemonMapper {
	List<Pokemon> findAll();
	Pokemon findById(Long id);
}
