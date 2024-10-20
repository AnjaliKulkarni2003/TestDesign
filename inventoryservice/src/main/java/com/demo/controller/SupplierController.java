package com.demo.controller;


import com.demo.dto.Supplier;
import com.demo.dto.SupplierResponse;

import com.demo.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}, path = "api/v1/supplier")
@Controller
@Slf4j
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @GetMapping(path = "/getAllLocalSuppliers")
    @ResponseBody
    public SupplierResponse getAllLocalSuppliers() {
        return supplierService.getAllLocalSuppliersResponse();

    }

    @GetMapping(path = "/getAllInternationalSuppliers")
    @ResponseBody
    public SupplierResponse getAllInternationalSuppliers() {
        return supplierService.getAllInternationalSuppliersResponse();

    }


    @PostMapping(path = "/updateSupplier")
    @ResponseStatus(CREATED)
    public void updateSupplier(@RequestBody Supplier supplier) {
        supplierService.addSupplier(supplier);
    }

    @PostMapping(path = "/deleteSupplier/{name}")
    @ResponseBody
    public int deleteStockWithType(@PathVariable("name") String name) {
        return supplierService.deleteSupplierByName(name);
    }

}
