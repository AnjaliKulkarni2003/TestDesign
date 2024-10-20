package com.demo.util.constants;

public enum SupplierType {
    Local("local"),
    International("International");

    private String description;

    SupplierType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
