package com.rollingpaper.ggeujeogggeujeog.common.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

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
			String storedFileName = createStoredFileName(originalFileName);
			Path path = Paths.get(getFullPath(storedFileName));
			imageFile.transferTo(path);
			return path.toString();
		} catch (IOException e) {
			log.info("Could not store a image file.", e);
			return "";
		}
	}

	private String createStoredFileName(String originalFileName) {
		String ext = extractExt(originalFileName);
		String uuid = UUID.randomUUID().toString();
		return uuid + "." + ext;
	}

	private String extractExt(String originalFileName) {
		int dotPos = originalFileName.lastIndexOf(".");
		return originalFileName.substring(dotPos + 1);
	}

	private String getFullPath(String fileName) {
		return uploadPath + fileName;
	}
}
