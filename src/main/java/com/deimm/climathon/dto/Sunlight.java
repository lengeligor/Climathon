package com.deimm.climathon.dto;

import lombok.Data;

@Data
public class Sunlight {

    //Okres	Mestská časť	Rok	Trvanie slnečného svitu za rok (v hodinách)
    private String region;
    private String cityPart;
    private String year;
    private String sunlight;
}
