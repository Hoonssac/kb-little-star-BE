package com.kbstar.littlestar.auth.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
public class SignupRequest {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    private String lastAnsweredDate;

    @NotNull(message = "나이를 입력해주세요.")
    @Min(value = 1, message = "나이는 1세 이상이어야 합니다.")
    private Integer age;

    @NotNull(message = "대표 포켓몬을 설정해주세요.")
    private Long mainPokemonId;

    private int mileage = 0;
}

