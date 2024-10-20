/*
 * Copyright (c) Giesecke+Devrient Mobile Security GmbH 2019-2023
 */
package com.demo.common;

import com.demo.entity.TtParts;
import com.demo.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Configuration
@EnableScheduling
public class InternationalSupplierInventoryCheckScheduler {
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private RestTemplate restTemplate; // Inject RestTemplate
    private static final String POST_ORDER_URL = "http://localhost:8081/api/v1/order/postOrder";
    @Scheduled(cron = "0 0 0 * * *") // Runs at 12:00 AM every day
    void checkInventory() {
        log.info("Check inventory Periodically after 1 hr ");
        List<TtParts> list = inventoryService.findTtPartsWithQuantityBelowThresholdForInternational();
        OrderRequest requestBody = createOrderList(List<TtParts> list);
        restTemplate.postForEntity(POST_ORDER_URL, requestBody, OrderRequest.class); // Adjust type if needed
    }
    private OrderRequest  createOrderList(List<TtParts> list){
        OrderRequest orderRequest = new OrderRequest();
        if (!list.isEmpty()) {
            log.info("Found {} items with low inventory, posting orders...", list.size());

            // Iterate over the list and make REST POST calls
            list.forEach(ttPart -> {
                try {
                    TtOrderPartsDto ttOrderPartsDto = new TtOrderPartsDto();
                    ttOrderPartsDto.setId(ttPart.getInventoryId());
                    ttOrderPartsDto.qty(ttPart.getThresholdQuantity());
                    ttOrderPartsDto.price(ttPart.getPrice()*ttPart.getThresholdQuantity());
                    // Assuming you have a request body (e.g., ttPart)
                    orderRequest.add(ttOrderPartsDto);
                    log.info("Order posted for part: {}", ttPart.getName());
                } catch (Exception e) {
                    log.error("Error posting order for part: {}", ttPart.getName(), e);
                }
            });
        } else {
            log.info("No items with low inventory.");
        }
        return orderRequest;
    }

}
