package com.api.book.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Helper {
	
	public final String uploadDir;
	
	
	public Helper()throws IOException {
		
		super();
		
	    uploadDir = new File("resources/static/images/").getAbsolutePath();
	    System.out.println(uploadDir);
		
	}
	
	public boolean uploadFile(MultipartFile multipartFile) {
		boolean f=false;
		
		try {
			
			Files.copy(multipartFile.getInputStream(), Paths.get(uploadDir+File.separator+multipartFile.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			f=true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return f;
	}
	
	

}