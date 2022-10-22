package com.deimm.climathon.dto;

import lombok.Data;

import java.util.Comparator;

@Data
public class Locality {

    private String region;
    private String cityPart;
    private String rain;
    private String sunlight;

    public static Comparator<Locality> localityComparatorByRain = new Comparator<Locality>() {
        public int compare(Locality s1, Locality s2) {
            if (s1 != null && s2 != null) {
                Long s1Rain
                        = Long.valueOf(s1.getRain());
                Long s2Rain
                        = Long.valueOf(s2.getRain());

                return s2Rain.compareTo(
                        s1Rain);
            } else {
                return 0;
            }
        }
    };

    public static Comparator<Locality> localityComparatorBySunlight = new Comparator<Locality>() {
        public int compare(Locality s1, Locality s2) {
            if (s1 != null && s2 != null) {
                Long s1Sunlight
                        = Long.valueOf(s1.getSunlight());
                Long s2Sunlight
                        = Long.valueOf(s2.getSunlight());

                return s2Sunlight.compareTo(
                        s1Sunlight);
            } else {
                return 0;
            }
        }
    };
}
