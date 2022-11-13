package com.rollingpaper.ggeujeogggeujeog.paper.infrastructure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import com.rollingpaper.ggeujeogggeujeog.common.util.FileNameUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LocalImageStorage implements ImageStorage {

	@Value("${spring.servlet.multipart.location}")
	private String uploadPath;

	@Override
	public void init() throws IOException {
		Files.createDirectories(Paths.get(uploadPath));
	}

	@Override
	public String store(MultipartFile imageFile) {
		if (imageFile.isEmpty()) {
			return "";
		}
		try {
			if (!Files.exists(Paths.get(uploadPath))) {
				init();
				log.info("Directory is created. Path : [" + uploadPath + "]");
			}

			String originalFileName = imageFile.getOriginalFilename();
			String storedFileName = FileNameUtil.createStoredFileName(originalFileName);
			Path path = Paths.get(getFullPath(storedFileName));
			imageFile.transferTo(path);
			return path.toString();
		} catch (IOException e) {
			log.error("Could not store a image file.", e);
			return "";
		}
	}

	@Override
	public boolean doesImageFileExist(String imageFileName) {
		return Files.exists(Paths.get(imageFileName));
	}

	private String getFullPath(String fileName) {
		return uploadPath + fileName;
	}
}
