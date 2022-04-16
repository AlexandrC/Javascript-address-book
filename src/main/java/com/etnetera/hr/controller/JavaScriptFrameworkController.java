package com.etnetera.hr.controller;

import com.etnetera.hr.dto.JsFrameworkDTO;
import com.etnetera.hr.service.JavaScriptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.etnetera.hr.entity.JsFrameworkEntity;

import javax.validation.Valid;
import java.util.List;

/**
 * Simple REST controller for accessing application logic.
 * 
 * @author Etnetera
 *
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/jsframework")
public class JavaScriptFrameworkController {


	private final JavaScriptService service;

	@Autowired
	public JavaScriptFrameworkController(JavaScriptService service) {
		this.service = service;
	}

	@GetMapping("/all")
	public Iterable<JsFrameworkDTO> frameworks() {
		return service.getAllFrameworks();
	}

	@GetMapping("/getById/{id}")
	public ResponseEntity<JsFrameworkDTO> getFrameworkById(@PathVariable("id") Long id){
		return new ResponseEntity<>(service.getFrameworkById(id), HttpStatus.ACCEPTED);
	}

	@GetMapping("/getByName/{name}")
	public List<JsFrameworkDTO> getFrameworkByName(@PathVariable("name") String name){
		return service.getFrameworkByName(name);
	}

	@PostMapping("/save")
	public ResponseEntity<JsFrameworkDTO> saveFramework(@Valid  @RequestBody JsFrameworkDTO jsFrameworkDTO){
		return new ResponseEntity<>(service.createFramework(jsFrameworkDTO), HttpStatus.ACCEPTED);
	}

	@PutMapping("/update/{JsFwId}")
	public JsFrameworkDTO updateFramework(@PathVariable("JsFwId") Long JsFwId, @RequestBody JsFrameworkDTO jsFrameworkDTO){
			return service.updateFrameworkById(jsFrameworkDTO,JsFwId);
	}

	@DeleteMapping("/delete/{JsFwId}")
	public HttpStatus deleteFramework(@PathVariable("JsFwId") Long jsFwId){
		return service.deleteFrameworkById(jsFwId);
	}



}
