package com.kbstar.littlestar.dto;

import lombok.*;
import java.util.List;

@Getter
@Setter
public class SignupRequest {
    private String username;
    private String password;
    private String lastAnsweredDate;
    private Integer age;
    private List<Integer> pokemonIds;
    private Integer mainPokemonId;
    private int mileage;
}

