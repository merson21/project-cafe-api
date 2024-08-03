package com.project.cafe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.project.cafe.POJO.Product;
import com.project.cafe.wrapper.ProductWrapper;

public interface ProductDao extends JpaRepository<Product, Integer>{
	
	@Query(value = "SELECT new com.project.cafe.wrapper.ProductWrapper(p.id, p.name, p.description, p.price, p.status, p.category.id, p.category.name) FROM Product p")
	List<ProductWrapper> getAllProduct();

	@Modifying
	@Transactional
	@Query(value = "UPDATE product SET status = ?1 WHERE id = ?2", nativeQuery = true)
	void updateProductStatus(String status, int id);

	@Query(value ="SELECT new com.project.cafe.wrapper.ProductWrapper(p.id, p.name) FROM Product p WHERE p.category.id=:id and p.status='true'")
	List<ProductWrapper> getProductByCategory(Integer id);
	
	@Query(value ="SELECT new com.project.cafe.wrapper.ProductWrapper(p.id, p.name, p.description, p.price) FROM Product p WHERE p.category.id=:id")
	ProductWrapper getProductById(Integer id);
}
