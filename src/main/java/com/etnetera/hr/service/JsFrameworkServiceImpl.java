package com.etnetera.hr.service;

import com.etnetera.hr.dto.JsFrameworkDTO;
import com.etnetera.hr.entity.JsFrameworkEntity;
import com.etnetera.hr.exceptions.JSDuplicate;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JsFrameworkServiceImpl implements JavaScriptServiceInterface {

    public final JavaScriptFrameworkRepository repository;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    public JsFrameworkServiceImpl(JavaScriptFrameworkRepository repository) {
        this.repository = repository;
    }


    public JsFrameworkDTO createFramework(JsFrameworkDTO jsFrameworkDTO) throws JSDuplicate {
        JsFrameworkEntity jsEntity = modelMapper.map(jsFrameworkDTO, JsFrameworkEntity.class);
        var collect = repository.findFirstByNameAndVersion(jsEntity.getName(), jsEntity.getVersion());
        if (collect == null) {
            var returnEntity = repository.save(jsEntity);
            return modelMapper.map(returnEntity, JsFrameworkDTO.class);
        } else {
            throw new JSDuplicate("Javascript framework with the same name : "+  jsFrameworkDTO.getName()
                    + " and version " + jsFrameworkDTO.getVersion()+ "already exist");
        }

    }

    public JsFrameworkDTO getFrameworkById(Long id) {
        var jsFrameworkEntity = repository.findById(id).orElseThrow(() -> new RuntimeException("Javascript framework was not found"));
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
        if (checkIfNameAndVersion(jsFrameworkDTO.getName(), jsFrameworkDTO.getVersion())){
            jsFrameworkEntity.setName(jsFrameworkDTO.getName());
            jsFrameworkEntity.setDate(jsFrameworkDTO.getDate());
            jsFrameworkEntity.setVersion(jsFrameworkDTO.getVersion());
            jsFrameworkEntity.setHypeLevel(jsFrameworkDTO.getHypeLevel());
            repository.save(jsFrameworkEntity);
            return modelMapper.map(jsFrameworkEntity, JsFrameworkDTO.class);
        }
        else {
            throw new RuntimeException("Conflict");
        }
    }

    private boolean checkIfNameAndVersion(String name, String version){
        return true;
    }

    public boolean deleteFrameworkById(Long id) {

        Optional<JsFrameworkEntity> jsFrameworkEntity = repository.findById(id);
        if (jsFrameworkEntity.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }


}
