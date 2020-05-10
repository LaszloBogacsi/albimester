package com.laszlobogacsi.albimester.persistance;

import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<BillData, Long> {
}
