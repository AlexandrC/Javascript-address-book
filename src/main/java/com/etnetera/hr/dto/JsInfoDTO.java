package com.etnetera.hr.dto;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class JsInfoDTO {

    private String name;
    private String version;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date date;
    private Integer hypeLevel;


}
