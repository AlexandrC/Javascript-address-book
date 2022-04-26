package com.etnetera.hr.service;

import com.etnetera.hr.dto.JsFrameworkDTO;
import com.etnetera.hr.entity.JsFrameworkEntity;
import com.etnetera.hr.exceptions.JSDuplicate;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@DisplayName("Javascript service tests")
public class JsFrameworkServiceImplTest {


    @MockBean
    private JavaScriptFrameworkRepository repository;
    private ModelMapper modelMapper;

    @Autowired
    JsFrameworkServiceImpl underTest;


    @Before
    public void setUp() {
       // MockitoAnnotations.initMocks(this);
        modelMapper = new ModelMapper();
    }


    @Test
    @DisplayName("Should save javascript framework")
    public void itShouldSaveJavaScriptFramework() {
        // Given
        Date expectedDate = new Date();
        JsFrameworkDTO expectedjsFrameworkDTO = new JsFrameworkDTO(1L,"Angular",
                "10.1",
                expectedDate,
                1);
        JsFrameworkEntity mockEntity = new JsFrameworkEntity(1L, "Angular",
                "10.1", expectedDate, 1);
        // When
        Mockito.when(repository.save(Mockito.any())).thenReturn(mockEntity);
        var actualJsFrameworkDTO = underTest.createFramework(expectedjsFrameworkDTO);

        // Then
        assertAll(
                () -> assertEquals(expectedjsFrameworkDTO.getName(), actualJsFrameworkDTO.getName()),
                () -> assertEquals(expectedjsFrameworkDTO.getVersion(), actualJsFrameworkDTO.getVersion()),
                () -> assertEquals(expectedjsFrameworkDTO.getDate(), actualJsFrameworkDTO.getDate()),
                () -> assertEquals(expectedjsFrameworkDTO.getHypeLevel(), actualJsFrameworkDTO.getHypeLevel())
        );

    }

    @Test
    public void itShouldThrowDuplicateExceptionWhenSaveFramework() {

        Date expectedDate = new Date();
        JsFrameworkDTO jsFrameworkDTO = new JsFrameworkDTO(1L,"Angular",
                "10.1",
                expectedDate,
                1);
        JsFrameworkEntity expectedEntity = new JsFrameworkEntity(1L, "Angular",
                "10.1", expectedDate, 3);
        // When
        Mockito.when(repository.findFirstByNameAndVersion(jsFrameworkDTO.getName(), jsFrameworkDTO.getVersion())).thenReturn(expectedEntity);

        // Then
         var assertThrows= assertThrows(JSDuplicate.class, () -> underTest.createFramework(jsFrameworkDTO), "Js");
         assertEquals(assertThrows.getMessage(),"Javascript framework with the same name : "+  jsFrameworkDTO.getName()
                 + " and version " + jsFrameworkDTO.getVersion()+ "already exist");
    }

//    }


    @Test
    public void itShouldUpdateFw() {
        // Given
        Date expectedDate = new Date();
        JsFrameworkDTO expectedJsFrameworkDTO = new JsFrameworkDTO(1L,"updateName", "10.5", expectedDate, 5);
        JsFrameworkEntity mockEntity = new JsFrameworkEntity(1L,
                "Angular",
                "10.1",
                expectedDate,
                2);
        // When
        Mockito.when(repository.findById(mockEntity.getId())).thenReturn(java.util.Optional.of(mockEntity));
        var actualJsDTO = underTest.updateFrameworkById(expectedJsFrameworkDTO, mockEntity.getId());
        // Then
        assertAll(
                () -> assertEquals(expectedJsFrameworkDTO.getName(), actualJsDTO.getName()),
                () -> assertEquals(expectedJsFrameworkDTO.getVersion(), actualJsDTO.getVersion()),
                () -> assertEquals(expectedJsFrameworkDTO.getDate(), actualJsDTO.getDate()),
                () -> assertEquals(expectedJsFrameworkDTO.getHypeLevel(), actualJsDTO.getHypeLevel())
        );
    }

    @Test
    public void itShouldThrowRuntimeExceptionWhenUpdateFw() {
        // Given
        String expectedMessage = "Javascript framework was not found";
        JsFrameworkDTO mockJsFrameworkDTO = new JsFrameworkDTO();
        Long mockId = null;
        // When
        // Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> underTest.updateFrameworkById(mockJsFrameworkDTO, mockId));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void itShouldThrowRuntimeExceptionWhenGetFwByID() {
        // Given
        String expectedMessage = "Javascript framework was not found";
        Long mockId = null;
        // When
        // Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> underTest.getFrameworkById(mockId));

        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void itShouldDeleteFw() {
        // Given
        var expectedReturn = true;
        JsFrameworkEntity mockEntity = new JsFrameworkEntity(1L,
                "Angular",
                "10.1",
                new Date(),
                2);
        // When
        Mockito.when(repository.findById(mockEntity.getId())).thenReturn(java.util.Optional.of(mockEntity));
        var actualReturn = underTest.deleteFrameworkById(mockEntity.getId());
        // Then
        assertEquals(expectedReturn, actualReturn);
    }

    @Test
    public void itShouldReturnNotFoundWhenDeleteFw() {
        // Given

        // When

        var expectedReturn = false;
        var actualReturn = underTest.deleteFrameworkById(1L);
        // Then
        assertEquals(expectedReturn, actualReturn);
    }


    @Test
    public void itShouldFindAllFrameworks() {
        // Given
        JsFrameworkEntity mockEntity = new JsFrameworkEntity(1L,
                "Angular",
                "10.1",
                new Date(),
                2);
        JsFrameworkEntity mockEntity2 = new JsFrameworkEntity(1L,
                "Angular",
                "10.6",
                new Date(),
                4);
        // When
        List<JsFrameworkEntity> entities = new ArrayList<>();
        entities.add(mockEntity);
        entities.add(mockEntity2);
        Mockito.when(repository.findAll()).thenReturn(entities);
        // Then
        assertEquals(underTest.getAllFrameworks().size(), 2);
    }

    @Test
    public void itShouldFindFrameworksByName() {
        // Given
        String searchName = "Angular";
        JsFrameworkEntity mockEntity = new JsFrameworkEntity(1L,
                "Angular",
                "10.1",
                new Date(),
                2);
        JsFrameworkEntity mockEntity2 = new JsFrameworkEntity(1L,
                "Angular",
                "10.6",
                new Date(),
                4);
        List<JsFrameworkEntity> entities = new ArrayList<>();
        entities.add(mockEntity);
        entities.add(mockEntity2);

        List<JsFrameworkDTO> expectedJsDTO = modelMapper.map(entities, new TypeToken<Iterable<JsFrameworkDTO>>() {
        }.getType());
        // When
        Mockito.when(repository.findAllByName(searchName)).thenReturn(entities);
        var actual = underTest.getFrameworkByName(searchName);
        // Then
        assertArrayEquals(expectedJsDTO.toArray(), actual.toArray());
    }

    @Test
    public void itShouldTestSingletonMethodMapper() {
        // Given
        String searchName = "Angular";
        JsFrameworkEntity mockEntity = new JsFrameworkEntity(1L,
                "Angular",
                "10.1",
                new Date(),
                2);
        // When
        Mockito.when(repository.findById(mockEntity.getId())).thenReturn(java.util.Optional.of(mockEntity));
        var actual = underTest.getFrameworkById(mockEntity.getId());
        var expected = modelMapper.map(mockEntity, JsFrameworkDTO.class);
        // Then
        assertAll(
                () -> assertEquals(expected.getName(), actual.getName()),
                () -> assertEquals(expected.getVersion(), actual.getVersion()),
                () -> assertEquals(expected.getDate(), actual.getDate()),
                () -> assertEquals(expected.getHypeLevel(), actual.getHypeLevel())
        );
    }
}