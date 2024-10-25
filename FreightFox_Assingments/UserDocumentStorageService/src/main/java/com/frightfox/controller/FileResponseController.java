package com.frightfox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.frightfox.service.IFileResponseService;

@RestController
@RequestMapping("/api/files/")
public class FileResponseController {
	
	@Autowired
	private IFileResponseService fileResponseService;
	
	@GetMapping("/search")
    public ResponseEntity<?> searchFiles(@RequestParam String userName, @RequestParam String searchTerm) {
        return ResponseEntity.ok(fileResponseService.searchFiles(userName, searchTerm));
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam String userName, @RequestParam String fileName) {
        try {
            byte[] fileData = fileResponseService.downloadFile(userName, fileName);
            return ResponseEntity.ok()
                    .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                    .body(fileData);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}
