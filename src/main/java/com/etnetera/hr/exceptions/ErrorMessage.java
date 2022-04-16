package com.etnetera.hr.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter @Setter
public class ErrorMessage {
    String error;
    String message;
    Integer status;
    @CreationTimestamp
    private LocalDateTime timestamp;

    public ErrorMessage(String error, String message, Integer status){
        this.error=error;
        this.message=message;
        this.status=status;
    }



}
