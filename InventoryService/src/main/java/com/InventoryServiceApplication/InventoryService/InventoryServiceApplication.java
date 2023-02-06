package com.InventoryServiceApplication.InventoryService;

import com.InventoryServiceApplication.InventoryService.model.Inventory;
import com.InventoryServiceApplication.InventoryService.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return args -> {
            Inventory inventorySet1 = new Inventory();
            inventorySet1.setSkuCode("iphone 2");
            inventorySet1.setQuantity(100);

            Inventory inventorySet2 = new Inventory();
            inventorySet1.setSkuCode("iphone 13");
            inventorySet1.setQuantity(200);

            inventoryRepository.save(inventorySet1);
            inventoryRepository.save(inventorySet2);
        };
    }
}
