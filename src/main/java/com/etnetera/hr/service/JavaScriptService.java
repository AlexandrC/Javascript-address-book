package com.etnetera.hr.service;

import com.etnetera.hr.dto.JsFrameworkDTO;
import com.etnetera.hr.entity.JsFrameworkEntity;
import com.etnetera.hr.exceptions.JSDuplicate;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JavaScriptService {

    public final JavaScriptFrameworkRepository repository;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    public JavaScriptService(JavaScriptFrameworkRepository repository) {
        this.repository = repository;
    }


    public JsFrameworkDTO createFramework(JsFrameworkDTO jsFrameworkDTO) throws JSDuplicate {
        JsFrameworkEntity jsEntity = modelMapper.map(jsFrameworkDTO, JsFrameworkEntity.class);
        var collect = repository.findFirstByNameAndVersion(jsEntity.getName(), jsEntity.getVersion());
        if (collect == null) {
            var returnEntity = repository.save(jsEntity);
            return modelMapper.map(returnEntity, JsFrameworkDTO.class);
        } else {
            throw new JSDuplicate("Javascript framework with the same name and version already exist");
        }
    }

    public JsFrameworkDTO getFrameworkById(Long id) {
        var jsFrameworkEntity = repository.findById(id).orElseThrow(() -> new RuntimeException("Javascript framework was not found"));
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(jsFrameworkEntity, JsFrameworkDTO.class);

    }

    public List<JsFrameworkDTO> getFrameworkByName(String name) {
        var jsFrameworkEntities = repository.findAllByName(name);
        return modelMapper.map(jsFrameworkEntities, new TypeToken<Iterable<JsFrameworkDTO>>() {
        }.getType());
    }

    public List<JsFrameworkDTO> getAllFrameworks() {
        Iterable<JsFrameworkEntity> entities = repository.findAll();
        return modelMapper.map(entities, new TypeToken<Iterable<JsFrameworkDTO>>() {
        }.getType());
    }

    public JsFrameworkDTO updateFrameworkById(JsFrameworkDTO jsFrameworkDTO, Long JsFwId) {
        JsFrameworkEntity jsFrameworkEntity = repository.findById(JsFwId).orElseThrow(() -> new RuntimeException("Javascript framework was not found"));
        jsFrameworkEntity.setName(jsFrameworkDTO.getName());
        jsFrameworkEntity.setDate(jsFrameworkDTO.getDate());
        jsFrameworkEntity.setVersion(jsFrameworkDTO.getVersion());
        jsFrameworkEntity.setHypeLevel(jsFrameworkDTO.getHypeLevel());
        repository.save(jsFrameworkEntity);
        return modelMapper.map(jsFrameworkEntity, JsFrameworkDTO.class);
    }

    public HttpStatus deleteFrameworkById(Long id) {

        Optional<JsFrameworkEntity> jsFrameworkEntity = repository.findById(id);
        if (jsFrameworkEntity.isPresent()) {
            repository.deleteById(id);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }


}
