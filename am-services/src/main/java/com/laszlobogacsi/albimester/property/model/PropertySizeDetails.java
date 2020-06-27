package com.laszlobogacsi.albimester.property.model;

import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Data
@Builder
public class PropertySizeDetails {
    private final int sizeM2;
    private final Optional<Integer> balconyM2;
    private final int fullSizeRoomNumber;
    private final Optional<Integer> halfSizeRoomNumber;

}
