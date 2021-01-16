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

    // get all
    @GetMapping("/all")
    public ResponseEntity<ResponseForm<List<ProductGetAll>>> getAllProducts() {
        List<ProductGetAll> productGetAll = productService.getAllProducts();
        return ResponseEntity.ok(ResponseForm.buildCustomResponse(productGetAll, 1, "okokok"));
    }

    // tim bang id
    @GetMapping("/find-by-id")
    public @ResponseBody
    ResponseEntity<ResponseForm<ProductCRUD>> getProductById(@RequestParam String idInput) {
        return ResponseEntity.ok(ResponseForm.buildCustomResponse(productService.getProductById(idInput), 1, "okokok"));
    }

    // sap xep priceOut
    @GetMapping("/get-price-out-order")
    public @ResponseBody
    ResponseEntity<ResponseForm<List<ProductCRUD>>> getProductByPriceWithOrder(@RequestParam Integer sortType) {
        return ResponseEntity.ok(ResponseForm.buildCustomResponse(productService.getProductByPriceWithOrder(sortType), 1, "okokok"));
    }

    // sap xep display
    @GetMapping("/get-display")
    public @ResponseBody
    ResponseEntity<ResponseForm<List<ProductCRUD>>> getProductByDisplayWithOrder(@RequestParam Integer sortType) {
        return ResponseEntity.ok(ResponseForm.buildCustomResponse(productService.getProductByDisplayWithOrder(sortType), 1, "okokok"));
    }

    // tim kiem theo ten khong phan biet hoa thuong
    @GetMapping("/find-by-name")
    public @ResponseBody
    ResponseEntity<ResponseForm<List<ProductCRUD>>> getProductByDisplay(@RequestParam String display) {
        return ResponseEntity.ok(ResponseForm.buildCustomResponse(productService.getProductByDisplay(display), 1, "okokok"));
    }

    // add product
    @PostMapping("/add-product")
    public @ResponseBody
    ResponseEntity<ResponseForm<Boolean>> addProduct(@RequestBody ProductCRUD productCRUD) {
        return ResponseEntity.ok(ResponseForm.buildCustomResponse(productService.addProduct(
                productCRUD.getProductID(), productCRUD.getDisplay(), productCRUD.getPriceIn(),
                productCRUD.getPriceOut(), productCRUD.getPriceSale(), productCRUD.getAmount(), productCRUD.getShipday(),
                productCRUD.getDescription(), productCRUD.getImages()), 1, "okokok"));
    }

    // update product bang id
    public @ResponseBody
    ResponseEntity<ResponseForm<Boolean>> updateProduct(@RequestBody ProductCRUD productCRUD) {
        return ResponseEntity.ok(ResponseForm.buildCustomResponse(productService.updateProduct(productCRUD), 1, "okokok"));
    }

    @GetMapping("/test-ex")
    public Integer testException(@RequestParam Integer input) {
        return input;
    }
}
