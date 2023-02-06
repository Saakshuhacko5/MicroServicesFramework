package com.Services.orderService.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "prod_orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String orderNum;
//    for a customer and address table
//    CascadeType.ALL specifies that when a Customer is created, if there is any Address association, then that Address will be created as well
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItemList;

}

