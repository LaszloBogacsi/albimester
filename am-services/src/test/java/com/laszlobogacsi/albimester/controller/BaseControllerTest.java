package com.laszlobogacsi.albimester.controller;

import com.google.gson.Gson;
import com.laszlobogacsi.albimester.property.model.*;

import java.util.Optional;

public class BaseControllerTest {
    protected static PropertyDetails.PropertyDetailsBuilder newPropertyDetails() {
        return PropertyDetails.builder()
                .propertyAddress(PropertyAddress.builder()
                        .city("Budapest")
                        .street("Petofi Sandor")
                        .type("utca")
                        .postCode("1119")
                        .houseNumber("27")
                        .floor(Optional.of("5"))
                        .door(Optional.empty())
                        .hrsz(Optional.of("12345"))
                        .build())
                .propertyType("Apartment")
                .buildType("Brick")
                .propertySizeDetails(PropertySizeDetails.builder()
                        .sizeM2(65)
                        .balconyM2(Optional.of(3))
                        .fullSizeRoomNumber(3)
                        .halfSizeRoomNumber(Optional.of(1))
                        .build());
    }

    protected String asJsonString(PropertyDetails propertyDetails) {
        final PropertyAddress address = propertyDetails.getPropertyAddress();
        final PropertySizeDetails sizeDetails = propertyDetails.getPropertySizeDetails();
        return new Gson().toJson(transformPropertyDetails(propertyDetails, address, sizeDetails));
    }

    private IncomingPropertyDetails transformPropertyDetails(PropertyDetails propertyDetails, PropertyAddress address, PropertySizeDetails sizeDetails) {
        return new IncomingPropertyDetails(
                new IncomingPropertyAddress(address.getCity(), address.getStreet(), address.getPostCode(), address.getType(), address.getHouseNumber(), address.getFloor().orElse(null), address.getDoor().orElse(null), address.getHrsz().orElse(null)),
                propertyDetails.getPropertyType(),
                propertyDetails.getBuildType(),
                new IncomingPropertySizeDetails(sizeDetails.getSizeM2(), sizeDetails.getBalconyM2().orElse(0), sizeDetails.getFullSizeRoomNumber(), sizeDetails.getHalfSizeRoomNumber().orElse(0))
        );
    }
}
