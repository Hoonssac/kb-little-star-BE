package com.kbstar.littlestar.auth.dto;

import lombok.*;

@Getter
public class SignupRequest {
    private String username;
    private String password;
    private String lastAnsweredDate;
    private Integer age;
    private Long mainPokemonId;
    private int mileage;
}

