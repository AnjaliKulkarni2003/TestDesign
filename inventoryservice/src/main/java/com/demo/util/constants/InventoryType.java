package com.demo.util.constants;

public enum InventoryType {
    TWO_WHEELER("TwoWheeler"),
    FOUR_WHEELER("FourWheeler"),;

    private String description;

    InventoryType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
