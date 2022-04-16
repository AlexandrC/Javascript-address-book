package com.etnetera.hr.service;

import com.etnetera.hr.dto.JsInfoDTO;
import com.etnetera.hr.entity.JavaScriptFrameworkEntity;
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


    public JavaScriptFrameworkEntity createFramework(JsInfoDTO jsInfoDTO) throws JSDuplicate{
        ModelMapper modelMapper = new ModelMapper();
        JavaScriptFrameworkEntity jsEntity = modelMapper.map(jsInfoDTO, JavaScriptFrameworkEntity.class);
        var collect = repository.findFirstByNameAndVersion(jsEntity.getName(),jsEntity.getVersion());
        if (collect == null){
            var returnEntity = repository.save(jsEntity);
            return returnEntity;
        }
        else {

            throw new JSDuplicate("Javascript framework with the same name and version already exist");
        }


    }
}
