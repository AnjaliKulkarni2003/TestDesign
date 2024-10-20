package order.dto;

import com.demo.util.constants.InventoryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryRequest {
    private Long inventoryId;
    private String code;
    private String name;
    private String  discription;
    private Integer quantity;
    private Supplier supplier;
    private Integer price;
    private InventoryType inventoryType;
}
