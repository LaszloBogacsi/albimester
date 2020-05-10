package com.laszlobogacsi.albimester.persistance;

import com.laszlobogacsi.albimester.parser.Bill;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class BillData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String billContent;

    private BillType billType;

    protected BillData() {
    }

    public BillData(BillType type, String billContent) {
        this.billType = type;
        this.billContent = billContent;
    }
}
