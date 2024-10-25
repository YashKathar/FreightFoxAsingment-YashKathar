package com.frightfox.service;

import java.util.List;

public interface IFileResponseService {
	List<String> searchFiles(String userName, String searchTerm);
	byte[] downloadFile(String userName, String fileName) throws Exception;
}
