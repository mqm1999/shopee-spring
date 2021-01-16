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

    //get all
    public List<ProductGetAll> getAllProducts() {
        String sql = "select * from Product;";
        List<ProductGetAll> product = jdbcTemplate.query(sql, new ProductMapper());
        return product;
    }

    // get by id
    public ProductCRUD getProductById(String idInput) {
        String sql = "select * from Product where `productID` = ?;";
        Object[] params = {idInput};
        ProductCRUD product = (ProductCRUD) jdbcTemplate.queryForObject(sql, new ProductCRUDMapper(), params);
        return product;
    }

    // check existed by id
    public boolean checkProductExistedById(String idInput) {
        String sql = "select exists (select * from Product where `productID` = ? and deleted = 0;);";
        Object[] params = {idInput};
        return jdbcTemplate.queryForObject(sql, Boolean.class, params);
    }

    // check existed by name
    public boolean checkProductExistedByName(String display) {
        String sql = "select exists (select * from Product where display = ? and deleted = 0);";
        Object[] params = {display};
        return jdbcTemplate.queryForObject(sql, Boolean.class, params);
    }

    // add
    public Integer addProduct(String productID, String display, int priceIn, int priceOut, int priceSale, int amount, int shipday, String description, String images) {
        String sql = "insert into Product (productID, display, priceIn, priceOut, priceSale, amount, shipday, description, images) values (?,?,?,?,?,?,?,?,?);";
        Object[] params = {productID, display, priceIn, priceOut, priceSale, amount, shipday, description, images};
        return jdbcTemplate.update(sql, params);
    }

    // get by price out asc
    public List<ProductCRUD> getAllPriceOutAsc(Integer sortType) {
        String sql = "select * from Product order by priceOut ASC and deleted = 0;";
        List<ProductCRUD> product = jdbcTemplate.query(sql, new ProductCRUDMapper());
        return product;
    }

    // get by price out desc
    public List<ProductCRUD> getAllPriceOutDesc(Integer sortType) {
        String sql = "select * from Product order by priceOut DESC and deleted = 0;";
        List<ProductCRUD> product = jdbcTemplate.query(sql, new ProductCRUDMapper());
        return product;
    }

    // get by display asc
    public List<ProductCRUD> getAllDisplayAsc(Integer sortType) {
        String sql = "select * from Product order by display ASC and deleted = 0;";
        List<ProductCRUD> product = jdbcTemplate.query(sql, new ProductCRUDMapper());
        return product;
    }

    // get by display desc
    public List<ProductCRUD> getAllDisplayDesc(Integer sortType) {
        String sql = "select * from Product order by display DESC and deleted = 0;";
        List<ProductCRUD> product = jdbcTemplate.query(sql, new ProductCRUDMapper());
        return product;
    }

    // get by display ignore lower/upper
    public List<ProductCRUD> getProductByDisplay(String display) {
        String sql = "select * from Product where lower(display) = lower(?) and deleted = 0;";
        // String sql = "select * from Product where display ilike ?;";
        Object[] params = {display};
        return jdbcTemplate.query(sql, new ProductCRUDMapper(), params);
    }

    public Integer updateProduct(ProductCRUD productCRUD) {
        String sql = "update Product set display = ?, priceIn = ?, priceOut = ?, priceSale = ?, amount = ?, shipday = ?, description = ?, images = ? where productID = ?;";
        return jdbcTemplate.update(sql, productCRUD.getDisplay(), productCRUD.getPriceIn(), productCRUD.getPriceOut(),
                productCRUD.getPriceSale(), productCRUD.getAmount(), productCRUD.getShipday(), productCRUD.getDescription(), productCRUD.getImages(), productCRUD.getProductID());
    }
}
