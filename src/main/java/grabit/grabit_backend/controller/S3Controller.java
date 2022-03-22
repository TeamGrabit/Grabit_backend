package grabit.grabit_backend.controller;

import grabit.grabit_backend.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/s3")
public class S3Controller {

	private S3Service s3Service;

	@Autowired
	public S3Controller(S3Service s3Service){
		this.s3Service = s3Service;
	}

	@PostMapping(value = "")
	public ResponseEntity<String> execWrite(MultipartFile file) throws IOException {
		System.out.println(file.getName());
		String result = s3Service.upload(file);

		return ResponseEntity.status(HttpStatus.OK).body(result);
	}
}
