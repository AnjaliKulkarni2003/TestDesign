package com.demo.entity;

import com.demo.dto.Supplier;
import com.demo.util.constants.InventoryType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "TT_PARTS")
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
    @NamedQuery(name = TtParts.FIND_BY_INVENTORYTYPE,
        query = "SELECT tfr FROM TtParts tfr WHERE tfr.inventoryType = :inventoryType"),
 })
public class TtParts {
    public static final String FIND_BY_INVENTORYTYPE = "TtParts.findByInventoryType";
    public static final String DELETE_BY_INVENTORYTYPE = "DELETE FROM TtParts p WHERE p.inventoryType = :inventoryType";
    public static final String FIND_BY_THRESHOLDLIMIT_SUPPLIERTYPE = "SELECT t FROM TtParts t WHERE t.quantity < t.thresholdQuantity AND t.supplier =::localSupplier";
    private Long inventoryId;
    private String code;
    private String name;
    private String  discription;
    private Integer thresholdQuantity;
    private Integer quantity;
    private Supplier supplier;
    private Integer price;
    private InventoryType inventoryType;
    @Id
    @Column(name = "INVENTORY_ID", nullable = false)
    @EqualsAndHashCode.Include
    public Long getInventoryId() {
        return inventoryId;
    }

    @Basic
    @Column(name = "INVENTORY_CODE", nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Basic
    @Column(name = "INVENTORY_NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "INVENTORY_DISCRIPTION", nullable = false)
    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    @Basic
    @Column(name = "INVENTORY_QTY", nullable = false)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    @Basic
    @Column(name = "INVENTORY_SUPPLIER", nullable = false)
    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Basic
    @Column(name = "INVENTORY_SUPPLIER", nullable = false)
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "INVENTORY_SUPPLIER", nullable = false)
    public InventoryType getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(InventoryType inventoryType) {
        this.inventoryType = inventoryType;
    }
    @Basic
    @Column(name = "INVENTORY_THRESHOLDQTY", nullable = false)
    public Integer getThresholdQuantity() {
        return thresholdQuantity;
    }

    public void getThresholdQuantity(Integer thresholdQuantity) {
        this.thresholdQuantity = thresholdQuantity;
    }
}
