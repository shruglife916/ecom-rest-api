package com.cogent.ecom.rest.api.service;

import com.cogent.ecom.rest.api.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> getProductsByCatId(Long catId);
    List<Product> getProductsBySubId(Long subId);
    Product getProductById(Long productId);
    Product getProductByCategoryAndSubCategoryId(Long catId, Long subId, Long productId);

    Product createProduct(Long catId, Long subId, Product newProduct);

    Product updateProduct(Long productId,Product updateProduct);

    void deleteProduct(Long productId);
}
