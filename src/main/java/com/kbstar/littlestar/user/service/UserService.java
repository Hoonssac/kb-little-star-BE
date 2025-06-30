package com.kbstar.littlestar.user.service;

import com.kbstar.littlestar.user.mapper.UserMapper;
import com.kbstar.littlestar.pokemon.mapper.PokemonMapper;
import com.kbstar.littlestar.user.domain.User;
import com.kbstar.littlestar.user.dto.UserResponse;
import com.kbstar.littlestar.user.mapper.UserPokemonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

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

    public boolean checkUserName(String username) {
        return userMapper.findByUserName(username) != null;
    }
}