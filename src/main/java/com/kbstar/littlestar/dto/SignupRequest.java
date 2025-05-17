package com.kbstar.littlestar.dto;

import lombok.*;

@Getter
@Setter
public class SignupRequest {
    private String username;
    private String password;
    private String lastAnsweredDate;
    private Integer age;
    private Long mainPokemonId;
    private int mileage;
}

