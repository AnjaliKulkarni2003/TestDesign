package order.controller;


import lombok.extern.slf4j.Slf4j;
import order.dto.OrderRequest;
import order.dto.OrderResponse;
import order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, path = "api/v1/order")
@Controller
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(path = "/getAllOrders")
    @ResponseBody
    public OrderResponse getAllOrders() {
        return orderService.getAllOrder();

    }


    @PostMapping(path = "/postOrder")
    @ResponseStatus(CREATED)
    public void updateOrder(@RequestBody OrderRequest orderRequest) {
         orderService.createOrder(orderRequest);
         orderService.postOrderInventoryUpdate(orderRequest);
    }

    @PostMapping(path = "/deleteOrder/{orderId}")
    @ResponseBody
    public int deleteOrder(@PathVariable("orderId") Integer orderId) {
        orderService.postOrderInventoryUpdateFromId(orderId);
        return orderService.cancelOrder(orderId);
    }


}
