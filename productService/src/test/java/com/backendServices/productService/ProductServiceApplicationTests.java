//package com.backendServices.productService;
//import com.backendServices.productService.dto.ProductRequest;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.DynamicPropertyRegistry;
//import org.springframework.test.context.DynamicPropertySource;
//import org.springframework.test.web.servlet.MockMvc;
//import org.testcontainers.containers.MongoDBContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
//import java.math.BigDecimal;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@Testcontainers
//@SpringBootTest
//@AutoConfigureMockMvc
//class ProductServiceApplicationTests {
//
//    //    firstly the test will start the mongoDb container by downloading the image 4.4.3
//    @Container
//    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.1");
//    @Autowired
//    private MockMvc mockMvc;
//    //    objectMapper will convert Pojo obj to Json or viceversa
//    @Autowired
//    private ObjectMapper objectMapper;
////    after getting the container it will get the replicaSet URL and add it to the springData URI property
////    at the time of creating the test.
//    @DynamicPropertySource
//    static void setProperty(DynamicPropertyRegistry dynamicPropertyRegistry) {
//        dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
//    }
//
//    @Test
//    void shouldCreateProduct() throws Exception {
//        ProductRequest productRequest = getProductRequest();
//        mockMvc.perform(post("/api/product")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(objectMapper.writeValueAsString(productRequest)))
//                .andExpect(status().isCreated());
//    }
//    private ProductRequest getProductRequest() {
//        return ProductRequest.builder()
//                .name("Iphone 12")
//                .description("iphone 12")
//                .price(BigDecimal.valueOf(56000))
//                .build();
//    }
////	We are just going to test the integration test and see whether the Get and Post API is working fine or not.
//
//}
