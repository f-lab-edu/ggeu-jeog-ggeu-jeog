package com.rollingpaper.ggeujeogggeujeog.authentication.domain;

public interface TokenRepository {

	void storeToken(String userEmail, String token);

	String getToken(String email);
}
