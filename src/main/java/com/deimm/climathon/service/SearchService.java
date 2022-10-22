package com.deimm.climathon.service;

import com.deimm.climathon.dto.Locality;
import com.deimm.climathon.dto.Raindrops;
import com.deimm.climathon.dto.Sunlight;
import com.deimm.climathon.filter.LocalityFilter;
import com.deimm.climathon.util.ReadCsv;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class SearchService {

    public Page<Locality> search(Pageable page, LocalityFilter filter) {
        ReadCsv readCsv = new ReadCsv();
        readCsv.fillLists();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        HashMap<String, Locality> sunlightLocality = getSunlightLocalityHashMap(readCsv, objectMapper);
        HashMap<String, Locality> raindropLocality = getRaindropLocalityHashMap(readCsv, objectMapper);

        HashMap<String, Locality> locality = new HashMap<>();
        sunlightLocality.values().forEach( loc -> {
            loc.setRain(raindropLocality.get(loc.getCityPart()).getRain());
            locality.put(loc.getCityPart(), loc);
        });

        List<Locality> localityList = new ArrayList<>(locality.values());

        if (filter.isRain()){
            localityList.sort(Locality.localityComparatorByRain);
        }
        if (filter.isSunlight()){
            localityList.sort(Locality.localityComparatorBySunlight);
        }

        return new PageImpl<>(localityList, page, localityList.size());
    }

    private HashMap<String, Locality> getSunlightLocalityHashMap(ReadCsv readCsv, ObjectMapper objectMapper) {
        HashMap<String, Locality> sunlightLocality = new HashMap<>();
        readCsv.getSunlight().forEach(s -> {
            Sunlight sunlight = objectMapper.convertValue(s, Sunlight.class);
            if (sunlightLocality.get(sunlight.getCityPart()) != null &&
                    sunlightLocality.get(sunlight.getCityPart()).getSunlight() != null &&
                    Double.parseDouble(sunlightLocality.get(sunlight.getCityPart()).getSunlight()) < Double.parseDouble(sunlight.getSunlight())) {
                sunlightLocality.put(sunlight.getCityPart(), objectMapper.convertValue(sunlight, Locality.class));
            } else if (sunlightLocality.get(sunlight.getCityPart()) == null ){
                sunlightLocality.put(sunlight.getCityPart(), objectMapper.convertValue(sunlight, Locality.class));
            }
        });
        return sunlightLocality;
    }

    private HashMap<String, Locality> getRaindropLocalityHashMap(ReadCsv readCsv, ObjectMapper objectMapper) {
        HashMap<String, Locality> raindropLocality = new HashMap<>();
        readCsv.getRaindrops().forEach(r -> {
            Raindrops raindrops = objectMapper.convertValue(r, Raindrops.class);
            if (raindropLocality.get(raindrops.getCityPart()) != null &&
                    raindropLocality.get(raindrops.getCityPart()).getRain() != null &&
                    Double.parseDouble(raindropLocality.get(raindrops.getCityPart()).getRain()) < Double.parseDouble(raindrops.getRain())) {
                raindropLocality.put(raindrops.getCityPart(), objectMapper.convertValue(raindrops, Locality.class));
            } else if (raindropLocality.get(raindrops.getCityPart()) == null ){
                raindropLocality.put(raindrops.getCityPart(), objectMapper.convertValue(raindrops, Locality.class));
            }
        });
        return raindropLocality;
    }
}
