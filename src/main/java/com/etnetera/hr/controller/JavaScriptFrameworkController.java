package com.etnetera.hr.controller;

import com.etnetera.hr.dto.JsFrameworkDTO;
import com.etnetera.hr.service.JsFrameworkServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Simple REST controller for accessing application logic.
 *
 * @author Etnetera
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/jsframework")
public class JavaScriptFrameworkController {


    private final JsFrameworkServiceImpl service;

    @Autowired
    public JavaScriptFrameworkController(JsFrameworkServiceImpl service) {
        this.service = service;
    }

    @GetMapping("/all")
    public Iterable<JsFrameworkDTO> frameworks() {
        return service.getAllFrameworks();
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<JsFrameworkDTO> getFrameworkById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(service.getFrameworkById(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/getByName/{name}")
    public List<JsFrameworkDTO> getFrameworkByName(@PathVariable("name") String name) {
        return service.getFrameworkByName(name);
    }

    @PostMapping("/save")
    public ResponseEntity<JsFrameworkDTO> saveFramework(@Valid @RequestBody JsFrameworkDTO jsFrameworkDTO) {
        var response = service.createFramework(jsFrameworkDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<JsFrameworkDTO> updateFramework(@PathVariable("id") Long id, @RequestBody JsFrameworkDTO jsFrameworkDTO) {
        var response = service.updateFrameworkById(jsFrameworkDTO, id);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus>  deleteFramework(@PathVariable("id") Long id) {
        if (service.deleteFrameworkById(id)){
            return new ResponseEntity<>(HttpStatus.OK) ;
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
