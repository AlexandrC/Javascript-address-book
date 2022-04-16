package com.etnetera.hr.controller;

import com.etnetera.hr.dto.JsInfoDTO;
import com.etnetera.hr.service.JavaScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.etnetera.hr.entity.JavaScriptFrameworkEntity;

import javax.validation.Valid;

/**
 * Simple REST controller for accessing application logic.
 * 
 * @author Etnetera
 *
 */
@RestController
@CrossOrigin(origins = "*")
public class JavaScriptFrameworkController {


	private final JavaScriptService service;

	@Autowired
	public JavaScriptFrameworkController(JavaScriptService service) {
		this.service = service;
	}

	@GetMapping("/frameworks")
	public Iterable<JavaScriptFrameworkEntity> frameworks() {
		//return repository.findAll();
		return null;
	}

	@PostMapping("/framework/save")
	public ResponseEntity<JavaScriptFrameworkEntity> saveFramework(@Valid  @RequestBody JsInfoDTO jsInfoDTO){
		return new ResponseEntity (service.createFramework(jsInfoDTO), HttpStatus.ACCEPTED);
	}

}
