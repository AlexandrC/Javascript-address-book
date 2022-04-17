package com.etnetera.hr.controller;

import com.etnetera.hr.dto.JsFrameworkDTO;
import com.etnetera.hr.service.JavaScriptService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(JavaScriptFrameworkControllerTest.class)
//public class JavaScriptFrameworkControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @MockBean
//    private JavaScriptService service;
//
//    public static String asJsonString(final Object obj) {
//        try {
//            return new ObjectMapper().writeValueAsString(obj);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @Test
//    void itShouldCreateAJSFramework() throws Exception {
//        //Given
//        JsFrameworkDTO jsFrameworkDTO = new JsFrameworkDTO("Angular","10.0.6", new Date(),1 );
//        //When
//        Mockito.when(service.createFramework(jsFrameworkDTO)).thenReturn(jsFrameworkDTO);
//        //Then
//        mockMvc.perform( MockMvcRequestBuilders
//                .post("/employees")
//                .content(asJsonString(jsFrameworkDTO))
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
//    }
//}
