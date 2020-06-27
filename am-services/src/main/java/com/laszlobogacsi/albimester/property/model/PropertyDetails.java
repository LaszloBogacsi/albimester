package com.laszlobogacsi.albimester.property.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PropertyDetails {
    private PropertyAddress propertyAddress;
    private String propertyType;
    private String buildType;
    private PropertySizeDetails propertySizeDetails;


}
