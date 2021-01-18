package com.nagp.product.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nagp.product.entity.Product;
import com.nagp.product.entity.ProductCategory;

@Repository
public interface ProductCategoryRepository extends MongoRepository<ProductCategory, String>{
	
	ProductCategory findByName(String name);
}
