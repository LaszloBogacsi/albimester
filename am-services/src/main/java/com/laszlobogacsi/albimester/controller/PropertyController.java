package com.laszlobogacsi.albimester.controller;

import com.laszlobogacsi.albimester.property.model.IncomingPropertyDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("property")
public class PropertyController {

    public PropertyController() {
    }

    @PostMapping(value = "/register", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Object> registerProperty(@RequestBody IncomingPropertyDetails propertyDetails) throws URISyntaxException {


        return ResponseEntity.created(new URI("some-location")).build();
    }
}
