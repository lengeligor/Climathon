package com.deimm.climathon.service;

import com.deimm.climathon.dto.Sunlight;
import com.deimm.climathon.filter.SunlightsFilter;
import com.deimm.climathon.util.ReadCsv;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SunlightService {

    public Page<Sunlight> getSunlight(Pageable page, SunlightsFilter filter) {
        ReadCsv readCsv = new ReadCsv();
        readCsv.fillLists();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Sunlight> sunlight = new ArrayList<>();
        readCsv.getSunlight().forEach(s -> {
            sunlight.add(objectMapper.convertValue(s, Sunlight.class));
        });
        List<Sunlight> filteredSunlight = new ArrayList<>(sunlight);
        Optional.ofNullable(filter.getRegion()).ifPresent(region -> {
            sunlight.forEach(r -> {
                if (!region.equals(r.getRegion()))
                    filteredSunlight.remove(r);
            });
        });
        Optional.ofNullable(filter.getCityPart()).ifPresent(cityPart -> {
            sunlight.forEach(r -> {
                if (!cityPart.equals(r.getCityPart()))
                    filteredSunlight.remove(r);
            });
        });
        Optional.ofNullable(filter.getYear()).ifPresent(year -> {
            sunlight.forEach(r -> {
                if (!year.equals(r.getYear()))
                    filteredSunlight.remove(r);
            });
        });
        Optional.ofNullable(filter.getSunlight()).ifPresent(sun -> {
            sunlight.forEach(r -> {
                if (!sun.equals(r.getSunlight()))
                    filteredSunlight.remove(r);
            });
        });
        return new PageImpl<>(filteredSunlight, page, filteredSunlight.size());
    }
}
