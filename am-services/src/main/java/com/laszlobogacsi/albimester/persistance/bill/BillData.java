package com.laszlobogacsi.albimester.persistance.bill;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.ClobType;

import javax.persistence.*;
import java.sql.Clob;

@Entity
@Getter
@Setter
public class BillData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String billContent;

    private BillType billType;

    protected BillData() {
    }

    public BillData(BillType type, String billContent) {
        this.billType = type;
        this.billContent = billContent;
    }
}
