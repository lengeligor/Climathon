package com.deimm.climathon.dto;

import lombok.Data;

@Data
public class Temperature {

    //Okres	Mestská časť	Rok	Teplota vzduchu v roku - priemerná na 1 d. m. (Celziov stupeň)
    private String region;
    private String cityPart;
    private String year;
    private String temperature;
}
