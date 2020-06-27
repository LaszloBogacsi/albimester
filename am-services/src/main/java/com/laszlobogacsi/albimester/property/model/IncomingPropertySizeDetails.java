package com.laszlobogacsi.albimester.property.model;

import lombok.Data;


@Data
public class IncomingPropertySizeDetails {
    private final int sizeM2;
    private final int balconyM2;
    private final int fullSizeRoomNumber;
    private final int halfSizeRoomNumber;
}
