package com.demo.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@Data
@JsonDeserialize(builder = StockResponse.StockResponseBuilder.class)
public class StockResponse {
    private final List<TwoWheelerParts> twowheelersParts;
    private final List<FourWheelerparts> fourwheelersParts;

    @Builder(toBuilder = true)
    public StockResponse(List<TwoWheelerParts> twowheelersParts, List<FourWheelerparts> fourwheelersParts) {
        this.twowheelersParts = twowheelersParts;
        this.fourwheelersParts = fourwheelersParts;
    }

    @JsonPOJOBuilder(withPrefix = "")
    public static final class StockResponseBuilder {

    }
}
