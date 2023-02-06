package com.InventoryServiceApplication.InventoryService.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_inventory")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String skuCode;
    private Integer quantity;
}
