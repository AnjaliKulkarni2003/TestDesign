package com.demo.dto;

import com.demo.util.constants.InventoryType;
import com.demo.util.constants.SupplierType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    private Long id;
    private String supplierName;
    private String address;
    private String phone;
    private String email;
    private SupplierType supplierType;
    private InventoryType inventoryType;
}
