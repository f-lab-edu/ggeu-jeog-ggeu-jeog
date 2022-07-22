package com.rollingpaper.ggeujeogggeujeog.common.util;

public interface PasswordEncoder {
	String encode(CharSequence password);

	boolean matches(CharSequence rawPassword, String encodedPassword);
}
