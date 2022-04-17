package com.etnetera.hr.repository;

import org.springframework.data.repository.CrudRepository;

import com.etnetera.hr.entity.JsFrameworkEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring data repository interface used for accessing the data in database.
 * 
 * @author Etnetera
 *
 */
@Repository
public interface JavaScriptFrameworkRepository extends CrudRepository<JsFrameworkEntity, Long> {


    JsFrameworkEntity findFirstByNameAndVersion(String Name, String version);

    List<JsFrameworkEntity> findAllByName(String Name);

}
