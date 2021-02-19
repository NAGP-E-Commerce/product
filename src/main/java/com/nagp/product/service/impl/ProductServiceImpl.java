package com.nagp.product.service.impl;

import java.util.List;

import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagp.product.entity.Product;
import com.nagp.product.repo.ProductRepository;
import com.nagp.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public Product getByProductId(String id) {
		Product product = productRepository.findByProductId(id);
		return product;
	}

	@Override
	@Transactional
	public Product saveProduct(Product product) {
		if (product.getId() != null) {
			throw new InvalidPropertyException(Product.class, "id", "id must be null");
		}
		Product dbCopy = productRepository.save(product);
		return dbCopy;
	}

	@Override
	@Transactional
	public Product updateProduct(Product product) {
		if (product.getId() == null) {
			throw new InvalidPropertyException(Product.class, "id", "id is either null or invalid");
		}
		Product dbCopy = productRepository.save(product);
		return dbCopy;
	}

	@Override
	public Boolean deleteProduct(String id) {
		Product product = getByProductId(id);
		if (product == null) {
			throw new InvalidPropertyException(Product.class, "id", "product is not present with the product id");
		}
		productRepository.deleteById(product.getId());
		return true;
	}

	@Override
	public Product findProductByCode(String code) {
		Product product = productRepository.findByCode(code);
		return product;
	}

	@Override
	public List<Product> findAll() {
		List<Product> productList = productRepository.findAll();
		return productList;
	}

	@Override
	public List<Product> findAllNewProducts() {
		List<Product> productList = productRepository.findByIsNew(true);
		return productList;
	}

	@Override
	public List<Product> findAllSpecialProducts() {
		List<Product> productList = productRepository.findByIsSpecial(true);
		return productList;
	}

	@Override
	public List<Product> findAllBestSellerProducts() {
		List<Product> productList = productRepository.findByIsBestSeller(true);
		return productList;
	}

	@Override
	public List<Product> findAllFeaturedProducts() {
		List<Product> productList = productRepository.findByIsFeatured(true);
		return productList;
	}
}
