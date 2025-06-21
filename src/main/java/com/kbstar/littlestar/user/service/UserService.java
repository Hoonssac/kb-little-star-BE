package com.kbstar.littlestar.user.service;

import com.kbstar.littlestar.common.exception.CustomException;
import com.kbstar.littlestar.common.exception.errorCode.PokemonErrorCode;
import com.kbstar.littlestar.pokemon.domain.Pokemon;
import com.kbstar.littlestar.pokemon.mapper.PokemonMapper;
import com.kbstar.littlestar.user.domain.User;
import com.kbstar.littlestar.user.dto.UserResponse;
import com.kbstar.littlestar.user.mapper.UserMapper;
import com.kbstar.littlestar.user.mapper.UserPokemonMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PokemonMapper pokemonMapper;
    private final UserMapper userMapper;

    // Dto 변환
    public UserResponse toUserResponse(User user) {
        List<Long> pokemonIds = user.getUserPokemons()
                .stream()
                .map(up -> up.getPokemon().getId())
                .collect(Collectors.toList());

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getAge(),
                user.getLastAnsweredDate(),
                user.getMileage(),
                user.getMainPokemonId(),
                pokemonIds
        );
    }

    @Transactional
    public void updateMainPokemon(Long userId, Integer mainPokemonId) {
        Pokemon pokemon = pokemonMapper.findById(Long.valueOf(mainPokemonId));
        if (pokemon == null) {
            throw new CustomException(PokemonErrorCode.POKEMON_NOT_FOUND);
        }
        userMapper.updateMainPokemon(userId, pokemon.getId());
    }

    public boolean checkUserName(String username) {
        return userMapper.findByUserName(username) != null;
    }
}