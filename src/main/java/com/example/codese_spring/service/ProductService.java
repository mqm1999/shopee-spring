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

    public Boolean addProduct(ProductCRUD productCRUD) {
        try {
            if (!productRepository.checkProductExistedById(productCRUD.getProductID()) && !productRepository.checkProductExistedByName(productCRUD.getDisplay())) {
                if (productRepository.addProduct(productCRUD) != 0) {
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

    public List<ProductCRUD> getProductByPriceWithOrder(Integer sortType) {
        try {
            switch (sortType) {
                case 0:
                    return productRepository.getAllPriceOutAsc(sortType);
                case 1:
                    return productRepository.getAllPriceOutDesc(sortType);
                default:
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Loi roi");
            return null;
        }
    }

    public List<ProductCRUD> getProductByDisplayWithOrder(Integer sortType) {
        try {
            switch (sortType) {
                case 0:
                    return productRepository.getAllDisplayAsc(sortType);
                case 1:
                    return productRepository.getAllDisplayDesc(sortType);
                default:
                    return null;
            }
        } catch (Exception e) {
            System.out.println("Loiiii");
            return null;
        }
    }

    public List<ProductCRUD> getProductByDisplay(String display) {
        try {
            return productRepository.getProductByDisplay(display);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public Boolean updateProduct(ProductCRUD productCRUD) {
        try {
            if (productRepository.checkProductExistedById(productCRUD.getProductID())) {
                if (productRepository.updateProduct1(productCRUD) != 0 && productRepository.checkProductExistedByName(productCRUD.getDisplay())) {
                    return true;
                } else {
                    System.out.println(productRepository.updateProduct(productCRUD));
                    return false;
                }
            } else {
                System.out.println(productRepository.checkProductExistedById(productCRUD.getProductID()));
                System.out.println(productRepository.checkProductExistedByName(productCRUD.getDisplay()));
                System.out.println("vao day roi");
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public Boolean deleteProduct(String productID) {
        try {
            if (productRepository.checkProductExistedById(productID) && productRepository.deleteProduct(productID) != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }
}
