package com.laszlobogacsi.albimester.property.model;

import lombok.Data;

@Data
public class IncomingPropertyDetails {
    private final IncomingPropertyAddress propertyAddress;
    private final String propertyType;
    private final String buildType;
    private final IncomingPropertySizeDetails propertySizeDetails;
}
