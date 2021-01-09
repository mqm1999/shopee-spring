package com.example.codese_spring.service;

import com.example.codese_spring.dto.ProductCRUD;
import com.example.codese_spring.dto.ProductGetAll;
import com.example.codese_spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public List<ProductGetAll> getAllProducts() {
        try {
            return productRepository.getAllProducts();
        } catch (Exception e) {
            System.out.println("Fail at repo");
        } finally {
            return productRepository.getAllProducts();
        }
    }

    public ProductCRUD getProductById(String idInput) {
        try {
            if (productRepository.checkProductExistedById(idInput)) {
                return productRepository.getProductById(idInput);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public boolean addProduct(String productID, String display, int priceIn, int priceOut, int priceSale, int amount, int shipday, String description, String images) {
        try {
            if (!productRepository.checkProductExistedById(productID) && !productRepository.checkProductExistedByName(display)) {
                if (productRepository.addProduct(productID, display, priceIn, priceOut, priceSale, amount, shipday, description, images) != 0) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
