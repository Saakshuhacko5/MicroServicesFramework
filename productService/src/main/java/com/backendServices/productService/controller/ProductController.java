package com.backendServices.productService.controller;

import com.backendServices.productService.dto.ProductDeleteId;
import com.backendServices.productService.dto.ProductRequest;
import com.backendServices.productService.dto.ProductResponse;
import com.backendServices.productService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//exposing a restAPI so add restController
@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor

public class ProductController {

//    create the API to create the products

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    as a req body we are going to receive the info
    public void createProduct(@RequestBody ProductRequest productRequest) {
        productService.createProduct(productRequest);
    }

    @DeleteMapping()
    public String deleteDepartmentById() {
        productService.deleteDeptListViaId();
        return "Product deleted Successfully";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }
}
