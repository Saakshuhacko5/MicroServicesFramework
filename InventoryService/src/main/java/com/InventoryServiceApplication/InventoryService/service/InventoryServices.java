package com.InventoryServiceApplication.InventoryService.service;

import com.InventoryServiceApplication.InventoryService.dto.InventoryResponse;
import com.InventoryServiceApplication.InventoryService.model.Inventory;
import com.InventoryServiceApplication.InventoryService.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServices {
    @Autowired
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> inStock(List<String> skuCode) throws InterruptedException {
        log.info("Wait Started");
        Thread.sleep(10000);
        log.info("Wait Ended");
        List<Inventory> inventoryList = inventoryRepository.findBySkuCodeIn(skuCode);
//        this::inventoryToDo means [inventory -> inventoryToDo(inventory)]
        return inventoryList.stream().map(this::inventoryToDo).collect(Collectors.toList());
    }

    private InventoryResponse inventoryToDo(Inventory inventory) {
        return InventoryResponse.builder()
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQuantity() > 0)
                .build();
    }
}
