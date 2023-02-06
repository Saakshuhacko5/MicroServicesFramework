package com.backendServices.productService.service;

import com.backendServices.productService.dto.ProductDeleteId;
import com.backendServices.productService.dto.ProductRequest;
import com.backendServices.productService.dto.ProductResponse;
import com.backendServices.productService.model.Product;
import com.backendServices.productService.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
//for logs
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
//       map the prod request to product module

        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
//        now object has been made of the product and now save it to db and access the repo layer

        productRepository.save(product);
        log.info("product is {} saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> mapToProductResponse(product)).collect(Collectors.toList());
    }

    public void deleteDeptListViaId() {
        productRepository.deleteAll();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}