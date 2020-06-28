package com.laszlobogacsi.albimester.property;

import com.laszlobogacsi.albimester.persistance.property.PropertyData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PropertyService {
    private PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public void save(PropertyData property) {
        propertyRepository.save(property);
    }

    public List<PropertyData> getByIds(List<Long> ids) {
        return StreamSupport.stream(propertyRepository.findAllById(ids).spliterator(), true)
                .collect(Collectors.toList());
    }
}
