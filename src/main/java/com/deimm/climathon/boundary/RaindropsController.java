package com.deimm.climathon.boundary;

import com.deimm.climathon.dto.Raindrops;
import com.deimm.climathon.filter.RaindropsFilter;
import com.deimm.climathon.service.RaindropsService;
import com.sun.istack.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/raindrops")
public class RaindropsController {

    private RaindropsService service;

    public RaindropsController(@NotNull RaindropsService service){
        this.service = service;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> getRaindrops(){
        return ResponseEntity.ok("Hello world");
    }

    @PostMapping("/list")
    public ResponseEntity<Page<Raindrops>> getRaindrops(Pageable page, @RequestBody RaindropsFilter filter){
        return ResponseEntity.ok(service.getRaindrops(page, filter));
    }
}
