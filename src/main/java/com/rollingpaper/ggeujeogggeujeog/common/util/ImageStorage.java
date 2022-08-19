package com.rollingpaper.ggeujeogggeujeog.common.util;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorage {

	void init() throws IOException;
	String store(MultipartFile imageFile);
}
