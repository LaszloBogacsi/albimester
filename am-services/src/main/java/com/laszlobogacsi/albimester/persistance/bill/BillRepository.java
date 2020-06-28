package com.laszlobogacsi.albimester.persistance.bill;

import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<BillData, Long> {
}
