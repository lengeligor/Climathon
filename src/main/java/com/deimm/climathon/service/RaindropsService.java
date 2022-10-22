package com.deimm.climathon.service;

import com.deimm.climathon.dto.Raindrops;
import com.deimm.climathon.filter.RaindropsFilter;
import com.deimm.climathon.util.ReadCsv;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RaindropsService {

    private static final ParameterizedTypeReference<List<Raindrops>> RAINDROPS = new ParameterizedTypeReference<List<Raindrops>>() {};

    public RaindropsService(){
    }

    public Page<Raindrops> getRaindrops(Pageable page, RaindropsFilter filter) {
        ReadCsv readCsv = new ReadCsv();
        readCsv.fillLists();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Raindrops> raindrops = new ArrayList<>();
        readCsv.getRaindrops().forEach(r -> {
            raindrops.add(objectMapper.convertValue(r, Raindrops.class));
        });
        List<Raindrops> filteredRaindrops = new ArrayList<>(raindrops);
        Optional.ofNullable(filter.getRegion()).ifPresent(region -> {
            raindrops.forEach(r -> {
                if (!region.equals(r.getRegion()))
                    filteredRaindrops.remove(r);
            });
        });
        Optional.ofNullable(filter.getCityPart()).ifPresent(cityPart -> {
            raindrops.forEach(r -> {
                if (!cityPart.equals(r.getCityPart()))
                    filteredRaindrops.remove(r);
            });
        });
        Optional.ofNullable(filter.getYear()).ifPresent(year -> {
            raindrops.forEach(r -> {
                if (!year.equals(r.getYear()))
                    filteredRaindrops.remove(r);
            });
        });
        Optional.ofNullable(filter.getRain()).ifPresent(rain -> {
            raindrops.forEach(r -> {
                if (!rain.equals(r.getRain()))
                    filteredRaindrops.remove(r);
            });
        });
        return new PageImpl<>(filteredRaindrops, page, filteredRaindrops.size());
    }
}
