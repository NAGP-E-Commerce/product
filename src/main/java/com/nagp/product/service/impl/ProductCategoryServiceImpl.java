package com.nagp.product.service.impl;

import java.util.List;

import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagp.product.entity.ProductCategory;
import com.nagp.product.repo.ProductCategoryRepository;
import com.nagp.product.service.ProductCategoryService;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	ProductCategoryRepository productCategoryRepository;

	@Override
	public ProductCategory getByProductCategoryName(String name) {
		ProductCategory productCategory = productCategoryRepository.findByName(name);
		return productCategory;
	}

	@Override
	public ProductCategory saveProductCategory(ProductCategory productCategory) {
		if (productCategory.getId() != null) {
			throw new InvalidPropertyException(ProductCategory.class, "id", "id must be null");
		}
		ProductCategory dbCopy = productCategoryRepository.save(productCategory);
		return dbCopy;
	}

	@Override
	public Boolean deleteProductCategory(String name) {
		ProductCategory productCategory = productCategoryRepository.findByName(name);
		if (productCategory == null) {
			throw new InvalidPropertyException(ProductCategory.class, "id",
					"Product Category is not present with the product category name");
		}
		productCategoryRepository.deleteById(productCategory.getId());
		return true;
	}

	@Override
	public ProductCategory updateProductCategory(ProductCategory productCategory) {
		return null;
	}

	@Override
	public List<ProductCategory> getCategories() {
		return productCategoryRepository.findAll();
	}
}
