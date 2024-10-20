package com.demo.service;


import com.demo.dto.Supplier;
import com.demo.dto.SupplierResponse;

import com.demo.entity.TtSupplier;

import com.demo.repository.SupplierRepository;

import com.demo.util.constants.SupplierType;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Slf4j
@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public SupplierService( SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Transactional(readOnly = true)
    public SupplierResponse getAllLocalSuppliersResponse() {
        return SupplierResponse.builder()
            .suppliers(getAllLocalSuppliers())
            .build();

    }
    @Transactional(readOnly = true)
    public SupplierResponse getAllInternationalSuppliersResponse() {
        return SupplierResponse.builder()
            .suppliers(getAllInternationalSuppliers())
            .build();

    }

    private List<Supplier> getAllLocalSuppliers() {
        return getLocalSuppliers(SupplierType.Local).stream().map(
            this::mapSupplierResponseLocal).toList();
    }

    private Supplier mapSupplierResponseLocal(TtSupplier ttSupplier) {
        return Supplier.builder()
            .supplierName(ttSupplier.getSupplierName())
            .address(ttSupplier.getAddress())
            .phone(ttSupplier.getName())
            .build();
    }
    private List<Supplier> getAllInternationalSuppliers() {
        return getLocalSuppliers(SupplierType.International).stream().map(
            this::mapSupplierResponseInternational).toList();
    }

    private Supplier mapSupplierResponseInternational(TtSupplier ttSupplier) {
        return Supplier.builder()
            .supplierName(ttSupplier.getSupplierName())
            .address(ttSupplier.getAddress())
            .phone(ttSupplier.getName())
            .build();
    }

    private  void createSupplier(Supplier supplier) {
        TtSupplier ttSupplier = new TtSupplier();
        ttSupplier.setSupplierName(supplier.getSupplierName());
        ttSupplier.setAddress(supplier.getAddress());
        ttSupplier.setSupplierType(supplier.getSupplierType());
        ttSupplier.setInventoryType(supplier.getInventoryType());
        supplierRepository.save(ttSupplier);

        log.info("Supplier {'name':'{}','address':'{}'} is Saved.",
            ttSupplier.getSupplierName(), ttSupplier.getAddress());
    }

    public List<TtSupplier> getLocalSuppliers(SupplierType supplierType) {
        TypedQuery<TtSupplier> query = entityManager.createNamedQuery(TtSupplier.FIND_BY_SUPPLIERTYPE, TtSupplier.class);
        query.setParameter("supplierType", supplierType);
        return query.getResultList();
    }
    public void addSupplier(Supplier supplier) {
        createSupplier(supplier);
    }

    public int deleteSupplierByName(String name) {
        Query query = entityManager.createNamedQuery(TtSupplier.DELETE_BY_SUPPLIERNAME);
        query.setParameter("supplierName", name);
        return query.executeUpdate();
    }
}
