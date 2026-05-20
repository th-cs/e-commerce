package com.dev.ecommerce.service;

import java.util.UUID;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PhotoService {

	@Value("${upload.dir}")
	private String uploadDir;

	public String savePhoto(MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		String fileExtension = fileName.substring(fileName.lastIndexOf("."));
		String UUIDFileName = UUID.randomUUID() + fileExtension;

		Path directory = Path.of(uploadDir);
		Files.createDirectories(directory);

		Path pathUrl = directory.resolve(UUIDFileName);
		Files.copy(file.getInputStream(), pathUrl);

		return uploadDir + "/" + UUIDFileName;
	}
}
