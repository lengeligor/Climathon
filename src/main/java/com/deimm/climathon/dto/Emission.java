package com.deimm.climathon.dto;

import lombok.Data;

@Data
public class Emission {

    private String year;
    private String monitoringStation;
    private String substance;
    private String value;
}
