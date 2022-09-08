package com.rollingpaper.ggeujeogggeujeog.common.util;

import java.util.UUID;

public class FileNameUtil {

	public static String createStoredFileName(String originalFileName) {
		String ext = extractExt(originalFileName);
		String uuid = UUID.randomUUID().toString();
		return uuid + "." + ext;
	}

	private static String extractExt(String originalFileName) {
		int dotPos = originalFileName.lastIndexOf(".");
		return originalFileName.substring(dotPos + 1);
	}
}
