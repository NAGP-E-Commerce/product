package com.nagp.product.service;

import java.util.List;

import com.nagp.product.dto.ProductDTO;
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

	List<ProductDTO> getAllESProductInfo();

	List<ProductDTO> getESProductDataByName(String productName);

	List<ProductDTO> getESProductDataByCode(String productCode);

	List<ProductDTO> getESProductDataByCategoryCode(String categoryCode);
}
