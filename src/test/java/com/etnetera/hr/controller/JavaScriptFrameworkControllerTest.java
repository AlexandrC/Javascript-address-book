package com.etnetera.hr.controller;

import com.etnetera.hr.Application;
import com.etnetera.hr.dto.JsFrameworkDTO;
import com.etnetera.hr.repository.JavaScriptFrameworkRepository;
import com.etnetera.hr.service.JsFrameworkServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = {Application.class})
@AutoConfigureMockMvc
public class JavaScriptFrameworkControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private JsFrameworkServiceImpl service;

    @Mock
    JavaScriptFrameworkRepository repository;


    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void itShouldCreateAJSFramework() throws Exception {
        //Given
        JsFrameworkDTO jsFrameworkDTO = new JsFrameworkDTO(1L,"Angular", "10.0.6", new Date(), 1);
        //When
        when(service.createFramework(any(JsFrameworkDTO.class))).thenReturn(jsFrameworkDTO);
        //Then
        var resultActions = mockMvc.perform(MockMvcRequestBuilders
                .post("/jsframework/save")
                .content(asJsonString(jsFrameworkDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(jsFrameworkDTO.getName()))
                .andExpect(jsonPath("$.version").value(jsFrameworkDTO.getVersion()));
        System.out.println(resultActions);
    }


    @Test
    public void itShouldFindFrameworkById() throws Exception {
        JsFrameworkDTO jsFrameworkDTO = new JsFrameworkDTO(1L,"Angular", "10.0.6", new Date(), 1);
        when(service.getFrameworkById(1L)).thenReturn(jsFrameworkDTO);

        var getFwRequest = mockMvc.perform(MockMvcRequestBuilders
                .get("/jsframework/getById/{id}", 1L)
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isAccepted())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.name").value(jsFrameworkDTO.getName()))
                .andExpect(jsonPath("$.version").value(jsFrameworkDTO.getVersion()));
    }

    @Test
    public void itShouldFindFrameworkByName() throws Exception {
        // Given
        String equalJsName = "Angular";
        List<JsFrameworkDTO> jsFrameworkDTOList = new ArrayList<>();
        JsFrameworkDTO jsFrameworkDTO = new JsFrameworkDTO(1L,equalJsName, "10.0.6", new Date(), 1);
        JsFrameworkDTO jsFrameworkDTO1 = new JsFrameworkDTO(1L,equalJsName, "11", new Date(), 1);
        jsFrameworkDTOList.add(jsFrameworkDTO);
        jsFrameworkDTOList.add(jsFrameworkDTO1);


        // When
        when(service.getFrameworkByName(equalJsName)).thenReturn(jsFrameworkDTOList);
        //Then
        var getFwRequest = mockMvc.perform(MockMvcRequestBuilders
                .get("/jsframework/getByName/{name}", "Angular")
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].name").value(jsFrameworkDTO.getName()))
                .andExpect(jsonPath("$[0].version").value(jsFrameworkDTO.getVersion()))
                .andExpect(jsonPath("$[1].name").value(jsFrameworkDTO1.getName()))
                .andExpect(jsonPath("$[1].version").value(jsFrameworkDTO1.getVersion()));
        System.out.println(getFwRequest);

    }

    @Test
    public void itShouldDeleteFramework() throws Exception {
        // Given

        // When
        when(service.deleteFrameworkById(any())).thenReturn(true);
        // Then
        var deleteReq = mockMvc.perform(MockMvcRequestBuilders
                .delete("/jsframework/delete/{id}", 1L)
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk());


    }

    @Test
    public void itShouldUpdateFramework() throws Exception {
        // Given
        Long mockId = 1L;
        JsFrameworkDTO jsFrameworkDTO = new JsFrameworkDTO(1L,"Angular", "10.0.6", new Date(), 1);
        //When
        when(service.updateFrameworkById(any(JsFrameworkDTO.class), any(Long.class))).thenReturn(jsFrameworkDTO);

        //Then
        var uptFwRequest = mockMvc.perform(MockMvcRequestBuilders
                .put("/jsframework/update/{id}", mockId)
                .content(asJsonString(jsFrameworkDTO))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name").value(jsFrameworkDTO.getName()))
                .andExpect(jsonPath("$.version").value(jsFrameworkDTO.getVersion()));


    }

    @Test
    public void itShouldFindAllFrameworks() throws Exception {
        // Given
        String equalJsName = "Angular";
        List<JsFrameworkDTO> jsFrameworkDTOList = new ArrayList<>();
        JsFrameworkDTO jsFrameworkDTO = new JsFrameworkDTO(1L,equalJsName, "10.0.6", new Date(), 1);
        JsFrameworkDTO jsFrameworkDTO1 = new JsFrameworkDTO(1L,equalJsName, "11", new Date(), 1);
        jsFrameworkDTOList.add(jsFrameworkDTO);
        jsFrameworkDTOList.add(jsFrameworkDTO1);


        // When
        when(service.getAllFrameworks()).thenReturn(jsFrameworkDTOList);
        //Then
        var getFwRequest = mockMvc.perform(MockMvcRequestBuilders
                .get("/jsframework/all")
                .accept(MediaType.ALL_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].name").value(jsFrameworkDTO.getName()))
                .andExpect(jsonPath("$[0].version").value(jsFrameworkDTO.getVersion()))
                .andExpect(jsonPath("$[1].name").value(jsFrameworkDTO1.getName()))
                .andExpect(jsonPath("$[1].version").value(jsFrameworkDTO1.getVersion()));
        System.out.println(getFwRequest);

    }
}

