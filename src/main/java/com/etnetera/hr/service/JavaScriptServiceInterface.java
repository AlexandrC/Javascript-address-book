package com.etnetera.hr.service;

import com.etnetera.hr.dto.JsFrameworkDTO;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface JavaScriptServiceInterface  {

    public JsFrameworkDTO createFramework(JsFrameworkDTO jsFrameworkDTO);

    public JsFrameworkDTO getFrameworkById(Long id);

    public List<JsFrameworkDTO> getFrameworkByName(String name);

    public List<JsFrameworkDTO> getAllFrameworks();

    public JsFrameworkDTO updateFrameworkById(JsFrameworkDTO jsFrameworkDTO, Long JsFwId);

    public HttpStatus deleteFrameworkById(Long id);
}
