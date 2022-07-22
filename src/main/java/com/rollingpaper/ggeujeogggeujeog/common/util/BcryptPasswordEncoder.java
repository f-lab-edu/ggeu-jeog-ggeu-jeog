package com.rollingpaper.ggeujeogggeujeog.common.util;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptPasswordEncoder implements PasswordEncoder {

	@Override
	public String encode(CharSequence password) {
		return BCrypt.hashpw((String) password, BCrypt.gensalt());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return BCrypt.checkpw((String) rawPassword, encodedPassword);
	}
}
