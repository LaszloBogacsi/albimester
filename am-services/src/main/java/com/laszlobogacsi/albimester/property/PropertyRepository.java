package com.laszlobogacsi.albimester.property;

import com.laszlobogacsi.albimester.persistance.property.PropertyData;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<PropertyData, Long> {
}
