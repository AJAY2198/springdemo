package com.example.product_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.product_api.entity.AppUser;
import com.example.product_api.entity.Product;
import com.example.product_api.repository.AppUserRepository;
import com.example.product_api.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private AppUserRepository appUserRepository;

	public Product createProduct(Long userid, Product product) {
		AppUser appUser = appUserRepository.findById(userid)
				.orElseThrow(() -> new RuntimeException("User id is not found: " + userid));

		product.setCreatedBy(appUser);
		return productRepository.save(product);
	}

	@Transactional(readOnly = true)
	public Product getProductById(Long id) {
		return productRepository.findProductWithUserById(id)
				.orElseThrow(() -> new RuntimeException("Product not found: " + id));
	}

	@Transactional(readOnly = true)
	public List<Product> getAllProducts() {
		return productRepository.findAllProductsWithUser();
	}

	@Transactional(readOnly = true)
	public List<Product> getProductsByUserId(Long userId) {
		return productRepository.findProductsWithUserByUserId(userId);
	}

	public Product updateProduct(Long id, Product updatedProduct) {
		Product existingProduct = getProductById(id);

		existingProduct.setName(updatedProduct.getName());
		existingProduct.setDescription(updatedProduct.getDescription());
		existingProduct.setPrice(updatedProduct.getPrice());
		existingProduct.setQuantity(updatedProduct.getQuantity());

		return productRepository.save(existingProduct);
	}

	// Delete product
	public void deleteProduct(Long id) {
		Product product = getProductById(id);
		productRepository.delete(product);
	}

	// Get user who created a product
	@Transactional(readOnly = true)
	public AppUser getUserByProductId(Long productId) {
		Product product = getProductById(productId);
		return product.getCreatedBy();
	}
	
	@Transactional
    public Product updateProductPartial(Long id, Product patchDTO) {
		Product product = getProductById(id);

        if (patchDTO.getName() != null) {
            product.setName(patchDTO.getName());
        }
        if (patchDTO.getPrice() != null) {
            product.setPrice(patchDTO.getPrice());
        }
        
        if (patchDTO.getDescription() != null) {
            product.setDescription(patchDTO.getDescription());
        }
        
        if (patchDTO.getQuantity() != null) {
            product.setQuantity(patchDTO.getQuantity());
        }

        return productRepository.save(product);
    }
	

}
