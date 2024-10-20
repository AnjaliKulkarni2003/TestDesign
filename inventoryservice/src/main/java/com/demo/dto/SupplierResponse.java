package com.demo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@JsonDeserialize(builder = SupplierResponse.SupplierResponseBuilder.class)
public class SupplierResponse {
    private final List<Supplier> suppliers;

    @Builder(toBuilder = true)
    public SupplierResponse( List<Supplier> suppliers) {
        this.suppliers = suppliers;

    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class SupplierResponseBuilder {

    }
}
