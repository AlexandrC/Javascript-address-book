package com.etnetera.hr.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class SingletonModelMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    private  SingletonModelMapper(){};

    public static ModelMapper getInstance(){
        return modelMapper;
    }

    }

