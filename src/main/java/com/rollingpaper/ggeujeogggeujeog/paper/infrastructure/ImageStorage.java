package com.rollingpaper.ggeujeogggeujeog.paper.infrastructure;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface ImageStorage {

	default void init() throws IOException {}
	String store(MultipartFile imageFile);
	boolean doesImageFileExist(String imageFileName);
}
