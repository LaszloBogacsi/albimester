package com.laszlobogacsi.albimester.controller;

import com.laszlobogacsi.albimester.persistance.property.PropertyData;
import com.laszlobogacsi.albimester.property.PropertyService;
import com.laszlobogacsi.albimester.property.model.PropertyDetails;
import org.springframework.beans.factory.annotation.Autowired;
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

    private PropertyService propertyService;

    @Autowired
    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping(value = "/register", produces = "application/json; charset=UTF-8")
    public ResponseEntity<Object> registerProperty(@RequestBody PropertyDetails propertyDetails) throws URISyntaxException {

        propertyService.save(new PropertyData(propertyDetails));

        return ResponseEntity.created(new URI("some-location")).build();
    }
}
