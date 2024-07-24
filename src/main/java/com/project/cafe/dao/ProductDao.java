package com.project.cafe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.cafe.POJO.Product;
import com.project.cafe.wrapper.ProductWrapper;

public interface ProductDao extends JpaRepository<Product, Integer>{
	
	@Query(value = "SELECT new com.project.cafe.wrapper.ProductWrapper(p.id, p.name, p.description, p.price, p.status, p.category.id, p.category.name) FROM Product p")
	List<ProductWrapper> getAllProduct();
}
