package com.nagp.product.mapping;

import java.util.ArrayList;
import java.util.List;

import com.nagp.product.dto.ProductCategoryDTO;
import com.nagp.product.dto.ProductDTO;
import com.nagp.product.entity.Product;
import com.nagp.product.entity.ProductCategory;

public class ProductCategoryMapping {

	public static ProductCategoryDTO getProductCategoryToProductCategoryDTO(ProductCategory productCategory) {
		if (productCategory == null || productCategory.getId() == null) {
			return null;
		}
		ProductCategoryDTO productCategoryDTO = new ProductCategoryDTO();
		productCategoryDTO.setId(productCategory.getId());
		productCategoryDTO.setName(productCategory.getName());
		productCategoryDTO.setSimilarCategory(productCategory.getSimilarCategory());
		productCategoryDTO.setSuperCategory(productCategory.getSuperCategory());
		List<ProductDTO> productDTO = new ArrayList<ProductDTO>();
		for(Product product : productCategory.getProduct()) {
			productDTO.add(ProductMapping.getProductToProductDTO(product));
		}
		productCategoryDTO.setProduct(productDTO);
		return productCategoryDTO;
	}
	
	public static ProductCategory getProductCategoryDTOToProductCategory(ProductCategoryDTO dto) {
		if (dto == null) {
			return null;
		}
		ProductCategory productCategory = new ProductCategory();
		productCategory.setId(dto.getId());
		productCategory.setName(dto.getName());
		productCategory.setSimilarCategory(dto.getSimilarCategory());
		productCategory.setSuperCategory(dto.getSuperCategory());
		return productCategory;
	}
}
