package com.cogent.ecom.rest.api.controller;

import com.cogent.ecom.rest.api.entity.Product;
import com.cogent.ecom.rest.api.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/api/v1/category")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/{categoryId}/{subCategoryId}/products")
    public ResponseEntity<Product> createProduct(@PathVariable("categoryId") Long catId,
                                                 @PathVariable("subCategoryId") Long subId,
                                                 @Valid @RequestBody Product product) {
        Product data = productService.createProduct(catId, subId, product);
        return new ResponseEntity<>(data, HttpStatus.CREATED);
    }

    @GetMapping("/{categoryId}/{subCategoryId}/{productId}")
    public ResponseEntity<Product> getProductByCategoryAndSubCategoryId(@PathVariable("categoryId") Long catId,
                                                                        @PathVariable("subCategoryId") Long subId,
                                                                        @PathVariable("productId") Long productId) {
        Product data = productService.getProductByCategoryAndSubCategoryId(catId, subId, productId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId) {
        Product data = productService.getProductById(productId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/subcategory/{subCategoryId}/products")
    public ResponseEntity<List<Product>> getProductsBySubCategoryId(@PathVariable("subCategoryId") Long subId) {
        List<Product> data = productService.getProductsBySubId(subId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/{categoryId}/products")
    public ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable("categoryId") Long catId) {
        List<Product> data = productService.getProductsByCatId(catId);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> data = productService.getAllProducts();
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Long productId,
                                                 @Valid @RequestBody Product product) {
        Product data = productService.updateProduct(productId, product);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
    }
}
