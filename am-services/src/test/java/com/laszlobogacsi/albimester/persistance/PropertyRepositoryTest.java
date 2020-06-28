package com.laszlobogacsi.albimester.persistance;

import com.laszlobogacsi.albimester.BaseTest;
import com.laszlobogacsi.albimester.persistance.property.PropertyData;
import com.laszlobogacsi.albimester.property.PropertyRepository;
import com.laszlobogacsi.albimester.property.model.PropertyDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest
public class PropertyRepositoryTest extends BaseTest {

    @Autowired
    PropertyRepository repository;
    PropertyData property;

    @BeforeEach
    void setUp() {
        PropertyDetails propertyDetails = newPropertyDetails().build();
        property = new PropertyData(propertyDetails);

    }

    @Test
    void canFindASavedPropertyById() {
        final PropertyData savedProperty = repository.save(property);
        assertThat(repository.findById(savedProperty.getId())).hasValue(savedProperty);
    }

}
