package com.laszlobogacsi.albimester;

import com.google.gson.Gson;
import com.laszlobogacsi.albimester.property.model.PropertyAddress;
import com.laszlobogacsi.albimester.property.model.PropertyDetails;
import com.laszlobogacsi.albimester.property.model.PropertySizeDetails;

public class BaseTest {
    protected static PropertyDetails.PropertyDetailsBuilder newPropertyDetails() {
        return PropertyDetails.builder()
                .propertyAddress(PropertyAddress.builder()
                        .city("Budapest")
                        .street("Petofi Sandor")
                        .type("utca")
                        .postCode("1119")
                        .houseNumber("27")
                        .floor("5")
                        .door(null)
                        .hrsz("12345")
                        .build())
                .propertyType("Apartment")
                .buildType("Brick")
                .propertySizeDetails(PropertySizeDetails.builder()
                        .sizeM2(65)
                        .balconyM2(3)
                        .fullSizeRoomNumber(3)
                        .halfSizeRoomNumber(1)
                        .build());
    }

    protected String asJsonString(PropertyDetails propertyDetails) {
        return toJson(propertyDetails);
    }

    private String toJson(Object o) {
        return new Gson().toJson(o);
    }

}
