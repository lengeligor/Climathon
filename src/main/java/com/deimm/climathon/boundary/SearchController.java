package com.deimm.climathon.boundary;

import com.deimm.climathon.dto.Locality;
import com.deimm.climathon.dto.Sunlight;
import com.deimm.climathon.filter.LocalityFilter;
import com.deimm.climathon.filter.SunlightsFilter;
import com.deimm.climathon.service.SearchService;
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
@RequestMapping(value = "/search")
public class SearchController {

    private SearchService service;

    public SearchController(@NotNull SearchService service){
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Page<Locality>> search(Pageable page, @RequestBody LocalityFilter filter){
        return ResponseEntity.ok(service.search(page, filter));
    }
}
