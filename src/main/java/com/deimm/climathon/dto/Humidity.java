package com.deimm.climathon.dto;

import lombok.Data;

@Data
public class Humidity {

    //Okres;Mestská časť;Rok;Relatívna (pomerná ) vlhkosť vzduchu v %
    private String region;
    private String cityPart;
    private String year;
    private String relativeHumidity;
}
