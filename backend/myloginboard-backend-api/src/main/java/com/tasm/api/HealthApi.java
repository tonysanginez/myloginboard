package com.tasm.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/health")
public class HealthApi {
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> health() {
		return new ResponseEntity<>("Health OK", HttpStatus.OK);
	}
	
}