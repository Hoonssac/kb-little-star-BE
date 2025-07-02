package com.kbstar.littlestar.user.service;

import com.kbstar.littlestar.auth.dto.SignupRequest;
import com.kbstar.littlestar.auth.service.AuthService;
import com.kbstar.littlestar.user.domain.User;
import com.kbstar.littlestar.user.dto.UserResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class UserPokemonServiceTest {

    @Autowired
    private UserPokemonService userPokemonService;

    @Autowired
    private AuthService authService;

    private HttpSession session;

    @BeforeEach
    void setUp() {
        session = new MockHttpSession();
        SignupRequest request = new SignupRequest();
        request.setUsername("testuser");
        request.setPassword("password");
        request.setAge(10);
        request.setMainPokemonId(1L);
        authService.signup(request, session);
    }

    @Test
    @DisplayName("메인 포켓몬 변경")
    void updateMainPokemon() {
        // given
        Long newMainPokemonId = 2L;

        // when
        UserResponse userResponse = userPokemonService.updateMainPokemon(newMainPokemonId, session);

        // then
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getMainPokemonId()).isEqualTo(newMainPokemonId);
    }

    @Test
    @DisplayName("포켓몬 뽑기")
    void gatchaPokemon() {
        // given
        Long newPokemonId = 3L;
        User beforeUser = (User) session.getAttribute("user");
        int beforePokemonCount = beforeUser.getUserPokemons().size();

        // when
        UserResponse userResponse = userPokemonService.gatchaPokemon(newPokemonId, session);

        // then
        assertThat(userResponse).isNotNull();
        assertThat(userResponse.getPokemonIds()).hasSize(beforePokemonCount + 1);
        assertThat(userResponse.getPokemonIds()).contains(newPokemonId);
    }
}
