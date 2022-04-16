package com.etnetera.hr.service;

import com.etnetera.hr.dto.JsFrameworkDTO;
import com.etnetera.hr.entity.JsFrameworkEntity;
import com.etnetera.hr.exceptions.JSDuplicate;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class JavaScriptServiceTest {

    @Mock
    JavaScriptFrameworkRepository repository;
    private  JavaScriptService underTest;



    @Before
    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
         underTest = new JavaScriptService(repository);
    }


    @Test
   public void itShouldSaveJavaScriptFramework(){
        // Given
        Date expectedDate = new Date();
        JsFrameworkDTO expectedjsFrameworkDTO = new JsFrameworkDTO("Angular",
                "10.1",
                expectedDate,
                1);
        JsFrameworkEntity mockEntity = new JsFrameworkEntity(1L,"Angular",
                "10.1",expectedDate,1);
        // When
        Mockito.when(repository.save(Mockito.any())).thenReturn(mockEntity);
        var actualJsFrameworkDTO=underTest.createFramework(expectedjsFrameworkDTO);

        // Then
        assertAll(
                ()-> assertEquals(expectedjsFrameworkDTO.getName(),actualJsFrameworkDTO.getName()),
                    ()-> assertEquals(expectedjsFrameworkDTO.getVersion(),actualJsFrameworkDTO.getVersion()),
                ()-> assertEquals(expectedjsFrameworkDTO.getDate(),actualJsFrameworkDTO.getDate()),
                ()-> assertEquals(expectedjsFrameworkDTO.getHypeLevel(),actualJsFrameworkDTO.getHypeLevel())
        );

    }

    @Test
    public void itShouldFindDuplicate(){

        Date expectedDate = new Date();
        JsFrameworkDTO jsFrameworkDTO = new JsFrameworkDTO("Angular",
                "10.1",
                expectedDate,
                1);
        JsFrameworkEntity expectedEntity = new JsFrameworkEntity(1L,"Angular",
                "10.1",expectedDate,3);
        // When
        Mockito.when(repository.findFirstByNameAndVersion(jsFrameworkDTO.getName(), jsFrameworkDTO.getVersion())).thenReturn(expectedEntity);

        // Then
        assertThrows(JSDuplicate.class, () -> underTest.createFramework(jsFrameworkDTO),
                "Javascript framework with the same name and version already exist");
    }

    @Test
    public void itShouldUpdateFw() {
        // Given
        Date expectedDate = new Date();
        JsFrameworkDTO expectedJsFrameworkDTO = new JsFrameworkDTO("updateName","10.5",expectedDate,5);
        JsFrameworkEntity mockEntity = new JsFrameworkEntity(1L,"Angular",
                "10.1",expectedDate,2);
        // When
        Mockito.when(repository.findById(mockEntity.getId())).thenReturn(java.util.Optional.of(mockEntity));
        var actualJsDTO= underTest.updateFrameworkById(expectedJsFrameworkDTO,mockEntity.getId());
        // Then
        assertAll(
                ()-> assertEquals(expectedJsFrameworkDTO.getName(),actualJsDTO.getName()),
                ()-> assertEquals(expectedJsFrameworkDTO.getVersion(),actualJsDTO.getVersion()),
                ()-> assertEquals(expectedJsFrameworkDTO.getDate(),actualJsDTO.getDate()),
                ()-> assertEquals(expectedJsFrameworkDTO.getHypeLevel(),actualJsDTO.getHypeLevel())
        );
    }

}