package com.api.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.book.helper.Helper;

@RestController
public class FileUploadController {

	
	private Helper fileHelper ;
	
	@Autowired
	public FileUploadController(Helper fileHelper) {
		this.fileHelper=fileHelper;
	}
	
	

	@PostMapping("/upload-file")
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
		try {
		
		if (file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Must choose a file");
		}

		if (!file.getContentType().equals("image/jpeg")) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Format must be of jpeg");
		}
		System.out.println("hi......................hi.....");
		boolean uploadFile = fileHelper.uploadFile(file);
		System.out.println("huuio..............");
		if (uploadFile) {
			return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(file.getOriginalFilename()).toUriString());
		} else {
			System.out.println("hiiiii.........");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File is not uploaded yet...");
		}
		}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured during file upload");
		}
	}

}