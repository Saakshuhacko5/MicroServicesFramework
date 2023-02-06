package com.Services.orderService.service;

import com.Services.orderService.dto.InventoryResponse;
import com.Services.orderService.dto.OrderLineItemsDto;
import com.Services.orderService.dto.OrderRequest;
import com.Services.orderService.model.Order;
import com.Services.orderService.model.OrderLineItems;
import com.Services.orderService.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClient;

    public String placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
//        The UUID is generated using a cryptographically strong pseudo random number generator.
        order.setOrderNum(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest
                .getOrderLineItemsDto()
                .stream()
                .map(this::mapToDto).toList();

        order.setOrderLineItemList(orderLineItems);

        List<String> skuCodesList = order
                .getOrderLineItemList()
                .stream()
                .map(OrderLineItems::getSkuCode).toList();

        InventoryResponse[] inventoryResponsesArray = webClient.build()
                .get()
                .uri("http://Inventory-Service/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodesList)
                                .build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        assert inventoryResponsesArray != null;
        boolean productsInStock = Arrays.stream(inventoryResponsesArray)
                .allMatch(InventoryResponse::isInStock);

//        Failed to resolve 'Inventory-Service' after 2 queries
//        Now this error will come when we have setted the port to 0 of Inventory service after 8081 port
//        So basically which instance to call it is confused so it should try to call the instance one by one
//        For that we have to use ClientSide Loadbalancing in Eureka Clients so add Bean of WebClient.Builder()
//        in WebClientConfig and @LoadBalanced Annotation.
        if (productsInStock) {
            orderRepository.save(order);

//        Call inventory service,and place  order if product is in stock.
            return "Order placed Successfully";
        } else {
            throw new IllegalArgumentException("Product is not in the stock");
        }
    }


    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItems.getPrice());
        orderLineItems.setQuantity(orderLineItems.getQuantity());
        orderLineItems.setSkuCode(orderLineItems.getSkuCode());
        return orderLineItems;
    }
}
