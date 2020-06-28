package com.laszlobogacsi.albimester.property.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class PropertySizeDetails {
    private int sizeM2;
    private int balconyM2;
    private int fullSizeRoomNumber;
    private int halfSizeRoomNumber;

}
