package com.etnetera.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.etnetera.hr.entity.JavaScriptFramework;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;

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

	private final JavaScriptFrameworkRepository repository;

	@Autowired
	public JavaScriptFrameworkController(JavaScriptFrameworkRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/frameworks")
	public Iterable<JavaScriptFramework> frameworks() {
		return repository.findAll();
	}

	@PostMapping("/framework/save")
	public ResponseEntity<JavaScriptFramework> saveFramework( @Valid  @RequestBody JavaScriptFramework javaScriptFramework){
		return new ResponseEntity (repository.save(javaScriptFramework), HttpStatus.ACCEPTED);
	}

}
