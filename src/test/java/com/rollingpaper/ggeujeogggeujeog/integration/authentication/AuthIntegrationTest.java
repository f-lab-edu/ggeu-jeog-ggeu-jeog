package com.rollingpaper.ggeujeogggeujeog.integration.authentication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rollingpaper.ggeujeogggeujeog.authentication.infrastructure.TokenRepository;
import com.rollingpaper.ggeujeogggeujeog.authentication.presentation.dto.SignInRequestDto;
import com.rollingpaper.ggeujeogggeujeog.authentication.presentation.dto.SignUpRequestDto;
import com.rollingpaper.ggeujeogggeujeog.authentication.presentation.dto.VerifyEmailRequestDto;
import com.rollingpaper.ggeujeogggeujeog.common.config.AbstractContainerBaseTest;
import com.rollingpaper.ggeujeogggeujeog.common.fixture.UserTestFixture;

@AutoConfigureMockMvc
class AuthIntegrationTest extends AbstractContainerBaseTest {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TokenRepository tokenRepository;

	@Test
	@Order(1)
	@DisplayName("POST /api/v1/users - 회원가입에 성공한다.")
	void successSignUp() throws Exception {
		//given
		SignUpRequestDto dto = new SignUpRequestDto(
			UserTestFixture.TestUser.USER2.getEmail(),
			UserTestFixture.TestUser.USER2.getPassword(),
			UserTestFixture.TestUser.USER2.getNickname()
		);

		//when, then
		this.mockMvc.perform(post("/api/v1/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	@Order(2)
	@DisplayName("POST /api/v1/users/authentication - 가입된 유저가 이메일 인증에 성공한다.")
	void successConfirmRegistration() throws Exception {
		//given
		VerifyEmailRequestDto dto = new VerifyEmailRequestDto(
			UserTestFixture.TestUser.USER2.getEmail(),
			tokenRepository.getToken(UserTestFixture.TestUser.USER2.getEmail())
		);

		//when, then
		this.mockMvc.perform(post("/api/v1/users/authentication")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@Test
	@DisplayName("POST /api/v1/user/sign-in - 미등록 유저는 로그인할 수 없다.")
	void failToSignIn() throws Exception {
		//given
		SignInRequestDto dto = new SignInRequestDto(
			UserTestFixture.TestUser.USER3.getEmail(),
			UserTestFixture.TestUser.USER3.getPassword()
		);

		//when, then
		this.mockMvc.perform(post("/api/v1/user/sign-in")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(dto)))
			.andDo(print())
			.andExpect(status().is4xxClientError());
	}

}
