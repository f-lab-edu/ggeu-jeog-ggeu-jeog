package com.rollingpaper.ggeujeogggeujeog.common.fixture;

import org.springframework.web.multipart.MultipartFile;

import com.rollingpaper.ggeujeogggeujeog.common.factory.ImageFile;

public class ImageTestFixture {

	public static String IMAGE_NAME1 = "test_image1.jpeg";
	public static MultipartFile MOCK_JPEG_FILE
		= ImageFile.createMockFile(IMAGE_NAME1, ImageFile.ImageFormat.JPEG);

	public static String IMAGE_NAME2 = "test_image2.png";
	public static MultipartFile MOCK_PNG_FILE
		= ImageFile.createMockFile(IMAGE_NAME2, ImageFile.ImageFormat.PNG);
}
