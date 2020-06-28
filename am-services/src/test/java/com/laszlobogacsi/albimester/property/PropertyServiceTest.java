package com.laszlobogacsi.albimester.property;

import com.laszlobogacsi.albimester.BaseTest;
import com.laszlobogacsi.albimester.persistance.property.PropertyData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class PropertyServiceTest extends BaseTest {
    private PropertyService service;
    private PropertyRepository propertyRepository;
    @BeforeEach
    void setUp() {
        propertyRepository = mock(PropertyRepository.class);
        service = new PropertyService(propertyRepository);
    }

    @Test
    void name() {
        PropertyData property = mock(PropertyData.class);
        Long id = 1L;
        property.setId(id);
        final List<Long> ids = singletonList(id);

        service.save(property);
        verify(propertyRepository, times(1)).save(property);

        when(propertyRepository.findAllById(ids)).thenReturn(Collections.singleton(property));
        final List<PropertyData> properties = service.getByIds(ids);
        verify(propertyRepository, times(1)).findAllById(ids);
        assertThat(properties.get(0)).isEqualToComparingFieldByField(property);
    }
}
