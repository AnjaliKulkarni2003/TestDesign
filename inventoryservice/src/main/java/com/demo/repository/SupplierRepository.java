package com.demo.repository;

import com.demo.entity.TtParts;
import com.demo.entity.TtSupplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<TtSupplier, String> {

}
