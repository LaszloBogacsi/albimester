package com.laszlobogacsi.albimester.upload;

import com.laszlobogacsi.albimester.persistance.BillData;
import com.laszlobogacsi.albimester.persistance.BillRepository;
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
