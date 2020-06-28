package com.laszlobogacsi.albimester.persistance.property;

import com.laszlobogacsi.albimester.property.model.PropertyDetails;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PropertyData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private PropertyDetails propertyDetails;

    protected PropertyData() {
    }

    public PropertyData(PropertyDetails propertyDetails) {
        this.propertyDetails = propertyDetails;
    }

}
