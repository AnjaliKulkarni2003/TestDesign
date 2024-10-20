package order.service;

import order.dto.InventoryRequest;
import order.dto.OrderRequest;

import com.demo.entity.TtParts;
import order.dto.OrderResponse;
import order.dto.TtOrderPartsDto;
import order.entity.TtOrder;
import order.entity.TtOrderParts;
import order.repository.OrderRepository;
import order.util.constants.InventoryType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import java.util.List;


import static order.util.constants.InventoryType.FOUR_WHEELER;
import static order.util.constants.InventoryType.TWO_WHEELER;

@Slf4j
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate; // Inject RestTemplate
    private static final String POST_ORDER_URL = "http://localhost:8080/api/v1/inventory/updateStock";
    @PersistenceContext
    private EntityManager entityManager;

    public OrderService(OrderRepository orderRepository) {

        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public OrderResponse getAllOrder() {
        return OrderResponse.builder()
            .orderList()
            .build();

    }

    private List<TtOrderParts> getAllOrders() {
        return orderRepository.findAll().stream().map(
            this::mapInventoryToInventoryResponse).toList();
    }

    private TtOrderParts mapInventoryToInventoryResponse(TtOrderPartsDto ttParts) {
        return TtOrderParts.builder()
            .id(ttParts.getid())
            .qty(ttParts.getQty())
            .price(ttParts.getPrice())
            .build();
    }

    public  void createOrder(OrderRequest orderRequest) {
        TtOrder ttOrder = new TtOrder();
        ttOrder.setOrderList(getAllOrders(orderRequest.getOrderLineItemsDtoList()));
        orderRepository.save(ttOrder);
    }

    public int cancelOrder(Integer orderId) {
        //todo
        return -1;
    }
    private int deletePartsByInventoryType() {
      return 0;
    }

    public  void postOrderInventoryUpdate(OrderRequest orderRequest) {
        InventoryRequest inventoryRequestBody = new InventoryRequest();
        restTemplate.postForEntity(POST_ORDER_URL, inventoryRequestBody, InventoryRequest.class); // Ad
    }
    public  void postOrderInventoryUpdateFromId(Integer id ) {
        //todo
        // find orderRequest based on id
        InventoryRequest inventoryRequestBody = new InventoryRequest();
        restTemplate.postForEntity(POST_ORDER_URL, inventoryRequestBody, InventoryRequest.class); // Ad
    }
}


