package com.devsuperior.bds02.controller;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.services.CityService;
import com.devsuperior.bds02.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/events")
public class EventController {


    @Autowired
    private EventService service;


    @PutMapping(value = "/{id}")
    public ResponseEntity<EventDTO> update (@PathVariable Long id,
                                            @RequestBody EventDTO dto) {

       dto =  service.update(id,dto);

        return ResponseEntity.ok().body(dto);

    }

}
