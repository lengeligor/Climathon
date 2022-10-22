package com.deimm.climathon.dto;

import lombok.Data;

@Data
public class Raindrops {

    //Okres	Mestská časť	Rok	Úhrn zrážok za rok (v mm)
    private String region;
    private String cityPart;
    private String year;
    private String rain;
}
