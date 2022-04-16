package com.etnetera.hr.repository;

import org.springframework.data.repository.CrudRepository;

import com.etnetera.hr.entity.JsFrameworkEntity;

/**
 * Spring data repository interface used for accessing the data in database.
 * 
 * @author Etnetera
 *
 */
public interface JavaScriptFrameworkRepository extends CrudRepository<JsFrameworkEntity, Long> {


    JsFrameworkEntity findFirstByNameAndVersion(String Name, String version);

}
