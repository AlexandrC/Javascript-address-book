package com.etnetera.hr.service;

import com.etnetera.hr.dto.JsFrameworkDTO;
import com.etnetera.hr.entity.JsFrameworkEntity;
import com.etnetera.hr.exceptions.JSDuplicate;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JavaScriptService {

    public final JavaScriptFrameworkRepository repository;



    @Autowired
    public JavaScriptService(JavaScriptFrameworkRepository repository) {
        this.repository = repository;
    }


    public JsFrameworkDTO createFramework(JsFrameworkDTO jsFrameworkDTO) throws JSDuplicate{
        ModelMapper modelMapper = new ModelMapper();
        JsFrameworkEntity jsEntity = modelMapper.map(jsFrameworkDTO, JsFrameworkEntity.class);
        var collect = repository.findFirstByNameAndVersion(jsEntity.getName(),jsEntity.getVersion());
        if (collect == null){
            var returnEntity = repository.save(jsEntity);
            return modelMapper.map(returnEntity,JsFrameworkDTO.class);
        }
        else {

            throw new JSDuplicate("Javascript framework with the same name and version already exist");
        }
    }

    public JsFrameworkDTO updateFrameworkById(JsFrameworkDTO jsFrameworkDTO, Long JsFwId){
        JsFrameworkEntity jsFrameworkEntity = repository.findById(JsFwId).orElseThrow(() -> new RuntimeException("Machine with specified machine Id was not found"));
        jsFrameworkEntity.setName(jsFrameworkDTO.getName());
        jsFrameworkEntity.setDate(jsFrameworkDTO.getDate());
        jsFrameworkEntity.setVersion(jsFrameworkDTO.getVersion());
        jsFrameworkEntity.setHypeLevel(jsFrameworkDTO.getHypeLevel());
        repository.save(jsFrameworkEntity);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(jsFrameworkEntity,JsFrameworkDTO.class);
    }





}
