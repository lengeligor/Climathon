package com.deimm.climathon.boundary;

import com.deimm.climathon.dto.Sunlight;
import com.deimm.climathon.filter.SunlightsFilter;
import com.deimm.climathon.service.SunlightService;
import com.sun.istack.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping(value = "/sunlight")
public class SunlightController {

    private SunlightService service;

    public SunlightController(@NotNull SunlightService service){
        this.service = service;
    }

    @PostMapping("/list")
    public ResponseEntity<Page<Sunlight>> getSunlight(Pageable page, @RequestBody SunlightsFilter filter){
        return ResponseEntity.ok(service.getSunlight(page, filter));
    }
}
