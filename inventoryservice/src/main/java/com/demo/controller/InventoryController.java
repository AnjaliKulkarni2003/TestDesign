package com.demo.controller;

import com.demo.dto.InventoryRequest;
import com.demo.dto.StockResponse;
import com.demo.service.InventoryService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, path = "api/v1/inventory")
@Controller
@Slf4j
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping(path = "/getAllStock")
    @ResponseBody
    public StockResponse getAllStock() {
        return inventoryService.getAllStock();

    }

    @GetMapping(path = "/getStock/{stockType}")
    @ResponseBody
    public StockResponse getStockWithType(@PathVariable("stockType") String stockType) {
        return inventoryService.getStockWithType(stockType);
    }

    @PostMapping(path = "/updateStock")
    @ResponseStatus(CREATED)
    public void updateStockWithType(@RequestBody InventoryRequest inventoryRequest) {
        inventoryService.addInventory(inventoryRequest);
    }

    @PostMapping(path = "/deleteStock/{stockType}")
    @ResponseBody
    public int deleteStockWithType(@PathVariable("stockType") String stockType) {
        return inventoryService.deleteInventory(stockType);
    }


}
