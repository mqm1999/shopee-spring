package com.example.codese_spring.controller;

import com.example.codese_spring.dto.ProductCRUD;
import com.example.codese_spring.dto.ProductGetAll;
import com.example.codese_spring.helper.ResponseBuilder.ResponseForm;
import com.example.codese_spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopee")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<ResponseForm<List<ProductGetAll>>> getAllProducts() {
        List<ProductGetAll> productGetAll = (List<ProductGetAll>) productService.getAllProducts();
        return ResponseEntity.ok(ResponseForm.buildCustomResponse(productGetAll, 1, "okokok"));
    }

    @GetMapping("/find-by-id")
    public @ResponseBody
    ResponseEntity<ResponseForm<ProductCRUD>> getProductById(@RequestParam String idInput) {
        return ResponseEntity.ok(ResponseForm.buildCustomResponse(productService.getProductById(idInput), 1, "okokok"));
    }

    @PostMapping("/add-product")
    public @ResponseBody ResponseEntity<ResponseForm<Boolean>> addProduct(@RequestParam String productID, @RequestParam String display, @RequestParam int priceIn, @RequestParam int priceOut,
                                            @RequestParam int priceSale, @RequestParam int amount, @RequestParam int shipday,
                                            @RequestParam String description, @RequestParam String images) {
        return ResponseEntity.ok(ResponseForm.buildCustomResponse(productService.addProduct(productID, display, priceIn, priceOut, priceSale, amount, shipday, description, images),1,"okokok"));
    }
}
