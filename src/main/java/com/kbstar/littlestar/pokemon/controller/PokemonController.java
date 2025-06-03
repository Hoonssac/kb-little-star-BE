package com.kbstar.littlestar.pokemon.controller;

import com.kbstar.littlestar.pokemon.domain.Pokemon;
import com.kbstar.littlestar.pokemon.repository.PokemonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/pokedex")
@RequiredArgsConstructor
public class PokemonController {
    private final PokemonRepository pokemonRepository;

    @GetMapping
    public ResponseEntity<List<Pokemon>> getAll() {
        return ResponseEntity.ok(pokemonRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getById(@PathVariable Long id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow();
        System.out.println(pokemon.getImageUrl());
        return ResponseEntity.ok(pokemon);
    }
}
