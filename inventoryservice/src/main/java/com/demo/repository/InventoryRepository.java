package com.demo.repository;

import com.demo.entity.TtParts;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InventoryRepository extends JpaRepository<TtParts, String> {

}
