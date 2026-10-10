package com.example.product_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.product_api.entity.AppUser;
import com.example.product_api.entity.Product;
import com.example.product_api.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	// Create product
	@PostMapping("/user/{userId}")
	public ResponseEntity<Product> createProduct(@PathVariable Long userId, @RequestBody Product product) {

		Product savedProduct = productService.createProduct(userId, product);

		return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
	}

	// Get all products
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

	// Get product by ID
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		return ResponseEntity.ok(productService.getProductById(id));
	}

	@GetMapping("/user/{userId}")
	public List<Product> getProductsByUserId(@PathVariable Long userId) {
		return productService.getProductsByUserId(userId);
	}

	@GetMapping("/{productId}/user")
	public AppUser getUserByProductId(@PathVariable Long productId) {
		return productService.getUserByProductId(productId);
	}

	// Update product
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {

		return ResponseEntity.ok(productService.updateProduct(id, product));
	}

	// Delete product
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.noContent().build();
	}
	
	@PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> patchProduct(@PathVariable Long id, @RequestBody Product patchDTO) {
        Product updatedProduct = productService.updateProductPartial(id, patchDTO);
        return ResponseEntity.ok(updatedProduct);
    }

}
