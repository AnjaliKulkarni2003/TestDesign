package com.demo.service;

import com.demo.dto.FourWheelerparts;
import com.demo.dto.InventoryRequest;
import com.demo.dto.StockResponse;

import com.demo.dto.Supplier;
import com.demo.dto.TwoWheelerParts;
import com.demo.entity.TtParts;
import com.demo.repository.InventoryRepository;
import com.demo.util.constants.InventoryType;
import com.demo.util.constants.SupplierType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;


import static com.demo.util.constants.InventoryType.FOUR_WHEELER;
import static com.demo.util.constants.InventoryType.TWO_WHEELER;

@Slf4j
@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public InventoryService( InventoryRepository inventoryRepository) {

        this.inventoryRepository = inventoryRepository;
    }

    @Transactional(readOnly = true)
    public StockResponse getAllStock() {
        return StockResponse.builder()
            .twowheelersParts(getAllTwoWheelerProducts())
            .fourwheelersParts(getAllFourWheelerProducts())
            .build();

    }
    public StockResponse getStockWithType(String stockType) {
        StockResponse stockResponseResult = null;
        if (stockType.equals(InventoryType.TWO_WHEELER.name())) {
            return getTwoWheelerStock();
        } else if (stockType.equals(InventoryType.FOUR_WHEELER.name())) {
            return getFourWheelerStock();
        }
        return stockResponseResult;

    }
    private StockResponse getTwoWheelerStock() {
        return StockResponse.builder()
            .twowheelersParts(getAllTwoWheelerProducts())
            .build();

    }
    private StockResponse getFourWheelerStock() {
        return StockResponse.builder()
            .fourwheelersParts(getAllFourWheelerProducts())
            .build();

    }
    private List<TwoWheelerParts> getAllTwoWheelerProducts() {
        return getPartsByInventoryType(TWO_WHEELER).stream().map(
            this::mapInventoryToInventoryResponseTWoWheeler).toList();
    }

    private TwoWheelerParts mapInventoryToInventoryResponseTWoWheeler(TtParts ttParts) {
        return TwoWheelerParts.builder()
            .inventoryId(ttParts.getInventoryId())
            .code(ttParts.getCode())
            .name(ttParts.getName())
            .discription(ttParts.getDiscription())
            .price(ttParts.getPrice())
            .supplier(ttParts.getSupplier())
            .build();
    }
    private List<FourWheelerparts> getAllFourWheelerProducts() {
        return getPartsByInventoryType(InventoryType.FOUR_WHEELER).stream().map(
            this::mapInventoryToInventoryResponseFourWheeler).toList();
    }

    private FourWheelerparts mapInventoryToInventoryResponseFourWheeler(TtParts ttParts) {
        return FourWheelerparts.builder()
            .inventoryId(ttParts.getInventoryId())
            .code(ttParts.getCode())
            .name(ttParts.getName())
            .discription(ttParts.getDiscription())
            .price(ttParts.getPrice())
            .supplier(ttParts.getSupplier())
            .build();
    }
    private  void createInventory(InventoryRequest inventoryRequest) {
        TtParts ttParts = new TtParts();
        ttParts.setInventoryType(inventoryRequest.getInventoryType());
        ttParts.setCode(inventoryRequest.getCode());
        ttParts.setDiscription(inventoryRequest.getDiscription());
        ttParts.setQuantity(inventoryRequest.getQuantity());
        ttParts.setPrice(inventoryRequest.getPrice());
        ttParts.setSupplier(inventoryRequest.getSupplier());
        inventoryRepository.save(ttParts);

        log.info("Product {'id':'{}', 'name':'{}','description':'{}','price':'{}','Supplier':'{}'} is Saved.",
            ttParts.getInventoryId(), ttParts.getName(), ttParts.getDiscription(), ttParts.getPrice(), ttParts.getSupplier());
    }

    public List<TtParts> getPartsByInventoryType(InventoryType inventoryType) {
        TypedQuery<TtParts> query = entityManager.createNamedQuery(TtParts.FIND_BY_INVENTORYTYPE, TtParts.class);
        query.setParameter("inventoryType", inventoryType);
        return query.getResultList();
    }
    public void addInventory(InventoryRequest inventoryRequest) {
        createInventory(inventoryRequest);
    }
    public int deleteInventory(String stockType) {
        if (stockType.equals(InventoryType.TWO_WHEELER.name())) {
           return deletePartsByInventoryType(TWO_WHEELER);
        }else if(stockType.equals(InventoryType.FOUR_WHEELER.name())) {
            return deletePartsByInventoryType(FOUR_WHEELER);
        }
        return -1;
    }
    private int deletePartsByInventoryType(InventoryType inventoryType) {
        Query query = entityManager.createNamedQuery(TtParts.DELETE_BY_INVENTORYTYPE);
        query.setParameter("inventoryType", inventoryType);
        return query.executeUpdate();
    }

    public List<TtParts> findTtPartsWithQuantityBelowThresholdForLocal() {
        return entityManager.createQuery(jpql, TtParts.class)
            .setParameter("localSupplier", SupplierType.Local)
            .getResultList();
        return query.getResultList();

    }
    public List<TtParts> findTtPartsWithQuantityBelowThresholdForInternational() {
        return entityManager.createQuery(jpql, TtParts.class)
            .setParameter("localSupplier", SupplierType.International)
            .getResultList();
        return query.getResultList();

    }
}


