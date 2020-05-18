package com.laszlobogacsi.albimester.bills;

import com.laszlobogacsi.albimester.bills.BillService;
import com.laszlobogacsi.albimester.persistance.BillData;
import com.laszlobogacsi.albimester.persistance.BillRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class BillServiceTest {
    private BillRepository billRepository;
    private BillService billService;

    @BeforeEach
    void setUp() {
        billRepository = mock(BillRepository.class);
        billService = new BillService(billRepository);
    }

    @Test
    void canSaveAndFindABill() {
        BillData billData = mock(BillData.class);
        Long id = 1L;
        billData.setId(id);
        final List<Long> ids = singletonList(id);

        billService.save(billData);
        verify(billRepository, times(1)).save(billData);

        when(billRepository.findAllById(ids)).thenReturn(Collections.singleton(billData));
        final List<BillData> bills = billService.getByIds(ids);
        verify(billRepository, times(1)).findAllById(ids);
        assertThat(bills.get(0)).isEqualToComparingFieldByField(billData);
    }
}
