package com.laszlobogacsi.albimester.bills;

import com.laszlobogacsi.albimester.persistance.bill.BillData;
import com.laszlobogacsi.albimester.persistance.bill.BillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BillService {
    private BillRepository billRepository;

    public BillService(BillRepository billRepository) {

        this.billRepository = billRepository;
    }

    public void save(BillData billData) {
        billRepository.save(billData);
    }

    public List<BillData> getByIds(List<Long> ids) {
        return StreamSupport.stream(billRepository.findAllById(ids).spliterator(), true)
                .collect(Collectors.toList());
    }
}
