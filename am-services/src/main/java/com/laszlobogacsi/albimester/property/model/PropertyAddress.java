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
public class PropertyAddress {
    private String city;
    private String street;
    private String postCode;
    private String type;
    private String houseNumber;
    private String floor;
    private String door;
    private String hrsz;
}
