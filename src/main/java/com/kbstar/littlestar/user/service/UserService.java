package com.kbstar.littlestar.user.service;

import com.kbstar.littlestar.pokemon.domain.Pokemon;
import com.kbstar.littlestar.user.entity.User;
import com.kbstar.littlestar.user.dto.UserResponse;
import com.kbstar.littlestar.pokemon.repository.PokemonRepository;
import com.kbstar.littlestar.user.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PokemonRepository pokemonRepository;

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
                user.getMainPokemon().getId(),
                pokemonIds
        );
    }

    @Transactional
    public void updateMainPokemon(User user, Integer mainPokemonId) {
        Pokemon pokemon = pokemonRepository.findById(Long.valueOf(mainPokemonId))
            .orElseThrow(() -> new IllegalArgumentException("해당 포켓몬이 존재하지 않습니다."));

        user.changeMainPokemon(pokemon);
        userRepository.save(user);
    }
}