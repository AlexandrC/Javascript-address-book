package com.etnetera.hr.service;

import com.etnetera.hr.dto.JsFrameworkDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface JavaScriptServiceInterface  {

     JsFrameworkDTO createFramework(JsFrameworkDTO jsFrameworkDTO);

     JsFrameworkDTO getFrameworkById(Long id);

    List<JsFrameworkDTO> getFrameworkByName(String name);

     List<JsFrameworkDTO> getAllFrameworks();

     JsFrameworkDTO updateFrameworkById(JsFrameworkDTO jsFrameworkDTO, Long JsFwId);

     boolean deleteFrameworkById(Long id);
}
