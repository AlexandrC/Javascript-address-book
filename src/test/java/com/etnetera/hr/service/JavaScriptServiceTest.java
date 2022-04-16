package com.etnetera.hr.service;

import com.etnetera.hr.dto.JsInfoDTO;
import com.etnetera.hr.entity.JavaScriptFrameworkEntity;
import com.etnetera.hr.exceptions.JSDuplicate;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

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
        JsInfoDTO jsInfoDTO = new JsInfoDTO("Angular",
                "10.1",
                expectedDate,
                1);
        JavaScriptFrameworkEntity expectedEntity = new JavaScriptFrameworkEntity(1L,"Angular",
                "10.1",expectedDate);
        // When
        Mockito.when(repository.save(Mockito.any())).thenReturn(expectedEntity);
        var returnvar=underTest.createFramework(jsInfoDTO);

        // Then
        Assert.assertEquals(expectedEntity, returnvar);

    }

    @Test
    public void itShouldFindDuplicate(){

        Date expectedDate = new Date();
        JsInfoDTO jsInfoDTO = new JsInfoDTO("Angular",
                "10.1",
                expectedDate,
                1);
        JavaScriptFrameworkEntity expectedEntity = new JavaScriptFrameworkEntity(1L,"Angular",
                "10.1",expectedDate);
        // When
        Mockito.when(repository.findFirstByNameAndVersion(jsInfoDTO.getName(), jsInfoDTO.getVersion())).thenReturn(expectedEntity);

        // Then
        assertThrows(JSDuplicate.class, () -> underTest.createFramework(jsInfoDTO),
                "Javascript framework with the same name and version already exist");
    }

}