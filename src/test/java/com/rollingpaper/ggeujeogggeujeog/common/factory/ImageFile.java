package com.rollingpaper.ggeujeogggeujeog.common.factory;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

public class ImageFile {

	public enum ImageFormat {
		JPEG("jpeg"),
		PNG("png");

		private String format;

		ImageFormat(String format) {
			this.format = format;
		}
	}

	public String getFormat(ImageFormat imageFormat) {
		return imageFormat.format;
	}

	public static MockMultipartFile createMockFile(String imageName, ImageFormat imageFormat) {
		switch (imageFormat) {
			case JPEG:
				return new MockMultipartFile(
					"image_jpeg",
					imageName,
					MediaType.IMAGE_JPEG_VALUE,
					new byte[10]
				);
			case PNG:
				return new MockMultipartFile(
					"image_png",
					imageName,
					MediaType.IMAGE_PNG_VALUE,
					new byte[10]
				);
			default:
				return new MockMultipartFile(
					"image",
					"image.png",
					MediaType.IMAGE_PNG_VALUE,
					new byte[10]
				);
		}
	}
}
