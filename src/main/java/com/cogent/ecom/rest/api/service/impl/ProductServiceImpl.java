package com.cogent.ecom.rest.api.service.impl;

import com.cogent.ecom.rest.api.entity.Category;
import com.cogent.ecom.rest.api.entity.Product;
import com.cogent.ecom.rest.api.entity.SubCategory;
import com.cogent.ecom.rest.api.repository.CategoryRepository;
import com.cogent.ecom.rest.api.repository.ProductRepository;
import com.cogent.ecom.rest.api.repository.SubCategoryRepository;
import com.cogent.ecom.rest.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    @Override
    public List<Product> getProductsByCatId(Long catId) {
        Category category = categoryRepository
                .findById(catId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        List<Product> products = productRepository.findByCategoryId(catId);
        return products;
    }

    @Override
    public List<Product> getProductsBySubId(Long subId) {
        SubCategory subCategory = subCategoryRepository
                .findById(subId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        List<Product> products = productRepository.findBySubCategoryId(subId);
        return products;
    }

    @Override
    public Product getProductById(Long productId) {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return product;
    }

    @Override
    public Product getProductByCategoryAndSubCategoryId(Long catId, Long subId, Long productId) {
        Category category = categoryRepository
                .findById(catId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        SubCategory subCategory = subCategoryRepository
                .findById(subId)
                .orElseThrow(() -> new RuntimeException("Subcategory not found"));
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        if(!subCategory.getCategory().getCatId().equals(category.getCatId()))
            throw new RuntimeException("SubCategory does not belong to this category");
        if(!product.getCategory().getCatId().equals(category.getCatId()))
            throw new RuntimeException("Product does not belong to this category");
        if(!product.getSubCategory().getSubId().equals(subCategory.getSubId()))
            throw new RuntimeException("Product does not belong to this subcategory");
        return product;
    }

    @Override
    public Product createProduct(Long catId, Long subId, Product newProduct) {
        Category category = categoryRepository
                .findById(catId)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        SubCategory subCategory = subCategoryRepository
                .findById(subId)
                .orElseThrow(() -> new RuntimeException("Subcategory not found"));
        newProduct.setCategory(category);
        newProduct.setSubCategory(subCategory);
        System.out.println(newProduct.getCategory().getCategoryName());
        System.out.println(newProduct.getSubCategory().getSubCategoryName());
        return productRepository.save(newProduct);
    }

    @Override
    public Product updateProduct(Long productId, Product updateProduct) {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setCategory(updateProduct.getCategory());
        product.setSubCategory(updateProduct.getSubCategory());
        product.setProductName(updateProduct.getProductName());
        product.setProductDescription(updateProduct.getProductDescription());
        product.setPrice(updateProduct.getPrice());
        product.setProductImage(updateProduct.getProductImage());
        product.setPosition(updateProduct.getPosition());
        product.setStatus(updateProduct.getStatus());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long productId) {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);
    }
}
