package com.example.codese_spring.helper.JdbcMapper;

import com.example.codese_spring.dto.ProductCRUD;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductCRUDMapper implements RowMapper {
    @Override
    public ProductCRUD mapRow(ResultSet resultSet, int i) throws SQLException {
        ProductCRUD productCRUD = new ProductCRUD();
        productCRUD.setProductID(resultSet.getString("productID"));
        productCRUD.setDisplay(resultSet.getString("display"));
        productCRUD.setAmount(resultSet.getInt("amount"));
        productCRUD.setPriceIn(resultSet.getInt("priceIn"));
        productCRUD.setPriceOut(resultSet.getInt("priceOut"));
        productCRUD.setDescription(resultSet.getString("description"));
        productCRUD.setShipday(resultSet.getInt("shipday"));
        productCRUD.setImages(resultSet.getString("images"));
        productCRUD.setPriceSale(resultSet.getInt("priceSale"));
        return productCRUD;
    }
}
