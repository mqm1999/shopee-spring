package com.example.codese_spring.dto;

import java.util.List;

public class ProductWithPagingDTO {
    List<ProductDTO> listProduct;
    PaginationDTO paginationDTO;

    public ProductWithPagingDTO() {
    }

    public ProductWithPagingDTO(List<ProductDTO> listProduct, PaginationDTO paginationDTO) {
        this.listProduct = listProduct;
        this.paginationDTO = paginationDTO;
    }

    public List<ProductDTO> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<ProductDTO> listProduct) {
        this.listProduct = listProduct;
    }

    public PaginationDTO getPaginationDTO() {
        return paginationDTO;
    }

    public void setPaginationDTO(PaginationDTO paginationDTO) {
        this.paginationDTO = paginationDTO;
    }
}
