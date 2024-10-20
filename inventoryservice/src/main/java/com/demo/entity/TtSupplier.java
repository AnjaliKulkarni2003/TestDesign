package com.demo.entity;


import com.demo.util.constants.InventoryType;
import com.demo.util.constants.SupplierType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TT_SUPPLIER")
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
    @NamedQuery(name = TtSupplier.FIND_BY_SUPPLIERTYPE,
        query = "SELECT TtSupplier FROM TtSupplier tfr WHERE TtSupplier.supplierType = :supplierType"),
    @NamedQuery(name = TtSupplier.DELETE_BY_SUPPLIERNAME,
        query = "DELETE TtSupplier FROM TtSupplier tfr WHERE TtSupplier.supplierName = :supplierName"),
 })
public class TtSupplier {
    public static final String FIND_BY_SUPPLIERTYPE = "TtSupplier.supplierType";
    public static final String DELETE_BY_SUPPLIERNAME = "DELETE FROM TtSupplier p WHERE p.supplierName = :supplierName";
    private Long id;
    private String supplierName;
    private String address;
    private SupplierType supplierType;
    private InventoryType inventoryType;

    @Id
    @Column(name = "ISUPPLIER_ID", nullable = false)
    @EqualsAndHashCode.Include
    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "SUPPLIER_NAME", nullable = false)
    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    @Basic
    @Column(name = "SUPPLIER_NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "SUPPLIER_ADDRESS", nullable = false)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Basic
    @Column(name = "SUPPLIER_TYPE", nullable = false)
    public SupplierType getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(SupplierType supplierType) {
        this.supplierType = supplierType;
    }

    @Basic
    @Column(name = "SUPPLIER_INVENTORYTYPE", nullable = false)
    public InventoryType getInventoryType() {
        return inventoryType;
    }

    public void setInventoryType(InventoryType inventoryType) {
        this.inventoryType = inventoryType;
    }
}
