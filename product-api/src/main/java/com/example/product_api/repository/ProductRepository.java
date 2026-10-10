package com.example.product_api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.product_api.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	
	
	 @Query("""
		        SELECT p FROM Product p
		        JOIN FETCH p.createdBy
		        WHERE p.id = :id
		    """)
		    Optional<Product> findProductWithUserById(@Param("id") Long id);

		    @Query("""
		        SELECT p FROM Product p
		        JOIN FETCH p.createdBy
		    """)
		    List<Product> findAllProductsWithUser();

		    @Query("""
		        SELECT p FROM Product p
		        JOIN FETCH p.createdBy
		        WHERE p.createdBy.id = :userId
		    """)
		    List<Product> findProductsWithUserByUserId(
		            @Param("userId") Long userId);
}
