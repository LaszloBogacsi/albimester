package com.laszlobogacsi.albimester.property.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PropertyDetails {
    @Embedded
    private PropertyAddress propertyAddress;
    private String propertyType;
    private String buildType;
    @Embedded
    private PropertySizeDetails propertySizeDetails;


}
