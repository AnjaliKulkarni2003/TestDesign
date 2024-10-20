package com.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FourWheelerparts {
    private Long inventoryId;
    private String code;
    private String name;
    private String  discription;
    private Integer quantity;
    private Supplier supplier;
    private Integer price;

}
