package com.InventoryServiceApplication.InventoryService.controller;

import com.InventoryServiceApplication.InventoryService.dto.InventoryResponse;
import com.InventoryServiceApplication.InventoryService.service.InventoryServices;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {


    @Autowired
    private final InventoryServices inventoryServices;
//  now we will create a bean in order to have a data stored in the table to see whether there
//  is any entry stored in InventoryServiceApplication
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> inStock(@RequestParam List<String> skuCode) throws InterruptedException {
        return inventoryServices.inStock(skuCode);
    }
}
