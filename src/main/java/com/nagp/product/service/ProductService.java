package com.nagp.product.service;

import java.util.List;

import com.nagp.product.entity.Product;

public interface ProductService {

	Product getByProductId(String id);
	
	Product saveProduct(Product product);
	
	Boolean deleteProduct(String id);
	
	Product updateProduct(Product product);
	
	Product findProductByCode(String code);
	
	List<Product> findAll();
	
	List<Product> findAllNewProducts();

	List<Product> findAllSpecialProducts();

	List<Product> findAllBestSellerProducts();

	List<Product> findAllFeaturedProducts();
}
