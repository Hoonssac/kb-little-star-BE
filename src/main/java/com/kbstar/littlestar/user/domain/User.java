package com.kbstar.littlestar.user.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kbstar.littlestar.auth.dto.SignupRequest;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class User {

    private Long id;
    private String username;
    private String password;
    private LocalDate lastAnsweredDate;
    private Integer age;
    private Integer mileage;
    private List<UserPokemon> userPokemons = new ArrayList<>();
    private Long mainPokemonId;

    public void addPokemon(UserPokemon userPokemon) {
        if (userPokemons == null) {
            userPokemons = new ArrayList<>();
        }
        // ✅ 동시성 안전을 위한 동기화
        synchronized (userPokemons) {
            // 중복 방지
            boolean exists = false;
            for (UserPokemon up : userPokemons) {
                if (up.getPokemon().getId().equals(userPokemon.getPokemon().getId())) {
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                userPokemons.add(userPokemon);
            }
        }
    }

    public User(Long id, String username, String password, LocalDate lastAnsweredDate,
        Integer age, Integer mileage, Long mainPokemonId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.lastAnsweredDate = lastAnsweredDate;
        this.age = age;
        this.mileage = mileage;
        this.mainPokemonId = mainPokemonId;
    }

    public static User of(SignupRequest request, String encodedPassword) {
        return User.builder()
            .username(request.getUsername())
            .password(encodedPassword)
            .age(request.getAge())
            .mileage(request.getMileage())
            .mainPokemonId(request.getMainPokemonId())
            .build();
    }

    public void updateMainPokemonId(Long mainPokemonId) {
        this.mainPokemonId = mainPokemonId;
    }

    public void useMileage(int useage) {
        this.mileage = this.mileage - useage;
    }

    // 포켓몬 리스트를 직접 설정하는 메서드 추가
    public void setPokemonList(List<UserPokemon> userPokemons) {
        this.userPokemons = userPokemons != null ? userPokemons : new ArrayList<>();
    }
}

