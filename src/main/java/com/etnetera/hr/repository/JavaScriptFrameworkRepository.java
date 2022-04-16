package com.etnetera.hr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.etnetera.hr.entity.JavaScriptFrameworkEntity;

import java.util.List;

/**
 * Spring data repository interface used for accessing the data in database.
 * 
 * @author Etnetera
 *
 */
public interface JavaScriptFrameworkRepository extends CrudRepository<JavaScriptFrameworkEntity, Long> {


    JavaScriptFrameworkEntity findFirstByNameAndVersion(String Name, String version);

}
