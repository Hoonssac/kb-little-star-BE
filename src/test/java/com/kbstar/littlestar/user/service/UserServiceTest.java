package com.kbstar.littlestar.user.service;

import com.kbstar.littlestar.auth.service.AuthService;
import com.kbstar.littlestar.user.domain.User;
import com.kbstar.littlestar.user.domain.UserPokemon;
import com.kbstar.littlestar.user.dto.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Test
    @DisplayName("유저 이름 중복 확인")
    void checkUserName() {
        // given
        String existingUsername = "testuser";

        // when
        boolean result = userService.checkUserName(existingUsername);

        // then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("User를 UserResponse로 변환")
    void toUserResponse() {
        // given
        User user = User.builder()
            .id(1L)
            .username("testuser")
            .age(10)
            .lastAnsweredDate(LocalDate.parse("2025-07-02"))
            .mileage(100)
            .mainPokemonId(1L)
            .userPokemons(new ArrayList<UserPokemon>())
            .build();

        // when
        UserResponse userResponse = userService.toUserResponse(user);

        // then
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getUsername()).isEqualTo(user.getUsername());
        assertThat(userResponse.getAge()).isEqualTo(user.getAge());
        assertThat(userResponse.getMileage()).isEqualTo(user.getMileage());
    }
}
