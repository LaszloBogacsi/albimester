package com.laszlobogacsi.albimester.persistance;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class BillData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Lob
    private String billContent;

    private BillType billType;

    protected BillData() {
    }

    public BillData(BillType type, String billContent) {
        this.billType = type;
        this.billContent = billContent;
    }
}
