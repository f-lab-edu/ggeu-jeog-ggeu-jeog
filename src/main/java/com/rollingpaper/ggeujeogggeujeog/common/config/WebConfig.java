package com.rollingpaper.ggeujeogggeujeog.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.rollingpaper.ggeujeogggeujeog.common.util.ImageStorage;
import com.rollingpaper.ggeujeogggeujeog.common.util.LocalImageStorage;

@Component
public class WebConfig {

	@Bean
	public ImageStorage imageStorage() {
		return new LocalImageStorage();
	}
}
