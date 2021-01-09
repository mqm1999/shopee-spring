package com.example.codese_spring.repository;

import com.example.codese_spring.dto.ProductCRUD;
import com.example.codese_spring.dto.ProductGetAll;
import com.example.codese_spring.helper.JdbcMapper.ProductCRUDMapper;
import com.example.codese_spring.helper.JdbcMapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<ProductGetAll> getAllProducts() {
        String sql = "select * from Product;";
        List<ProductGetAll> product = jdbcTemplate.query(sql, new ProductMapper());
        return product;
    }

    public ProductCRUD getProductById(String idInput) {
        String sql = "select * from Product where `productID` = ?;";
        Object[] params = {idInput};
        ProductCRUD product = (ProductCRUD) jdbcTemplate.queryForObject(sql, new ProductCRUDMapper(), params);
        return product;
    }

    public boolean checkProductExistedById(String idInput) {
        String sql = "select exists (select * from Product where `productID` = ?);";
        Object[] params = {idInput};
        return jdbcTemplate.queryForObject(sql, Boolean.class, params);
    }

    public boolean checkProductExistedByName(String display) {
        String sql = "select exists (select * from Product where display = ?);";
        Object[] params = {display};
        return jdbcTemplate.queryForObject(sql, Boolean.class, params);
    }

    public int addProduct(String productID, String display, int priceIn, int priceOut, int priceSale, int amount, int shipday, String description, String images) {
        String sql = "insert into Product (productID, display, priceIn, priceOut, priceSale, amount, shipday, description, images) values (?,?,?,?,?,?,?,?,?);";
        Object[] params = {productID, display, priceIn, priceOut, priceSale, amount, shipday, description, images};
        return jdbcTemplate.update(sql, params);
    }
}
