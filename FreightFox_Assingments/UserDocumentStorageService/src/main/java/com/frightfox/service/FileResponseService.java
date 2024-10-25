package com.frightfox.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FileResponseService implements IFileResponseService {
	
	 @Autowired
	 private AmazonS3 s3client;

	 private static final String USER_FOLDER_PREFIX = "users/";
	
	@Override
	public List<String> searchFiles(String userName, String searchTerm) {
		return s3client.listObjects(USER_FOLDER_PREFIX + userName).getObjectSummaries().stream()
                .filter(file -> file.getKey().contains(searchTerm))
                .map(S3ObjectSummary::getKey)
                .collect(Collectors.toList());
	}

	@Override
	public byte[] downloadFile(String userName, String fileName) throws Exception {
		InputStream inputStream = s3client.getObject(USER_FOLDER_PREFIX + userName, fileName).getObjectContent();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        return outputStream.toByteArray();
	}

}
