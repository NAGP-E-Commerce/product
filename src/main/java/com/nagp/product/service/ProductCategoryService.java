package com.nagp.product.service;

import java.util.List;

import com.nagp.product.entity.ProductCategory;

public interface ProductCategoryService {

	ProductCategory getByProductCategoryName(String name);
	
	ProductCategory saveProductCategory(ProductCategory productCategory);
	
	Boolean deleteProductCategory(String name);
	
	ProductCategory updateProductCategory(ProductCategory productCategory);
	
	List<ProductCategory> getCategories();
}
