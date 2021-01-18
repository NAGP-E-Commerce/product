package com.nagp.product.mapping;

import com.nagp.product.dto.ProductDTO;
import com.nagp.product.entity.Product;

public class ProductMapping {

	public static ProductDTO getProductToProductDTO(Product product) {
		if (product == null || product.getId() == null) {
			return null;
		}
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setProductId(product.getProductId());
		productDTO.setName(product.getName());
		productDTO.setCode(product.getCode());
		productDTO.setTitle(product.getTitle());
		productDTO.setDescription(product.getDescription());
		productDTO.setBrand(product.getBrand());
		productDTO.setPrice(product.getPrice());
		productDTO.setPrimaryImageUrl(product.getPrimaryImageUrl());
		productDTO.setSecondaryImageUrl(product.getSecondaryImageUrl());
		productDTO.setCategoryCode(product.getCategoryCode());
		productDTO.setUnit(product.getUnit());
		productDTO.setActive(product.getActive());
		productDTO.setIsNew(product.getIsNew());
		productDTO.setIsFeatured(product.getIsFeatured());
		productDTO.setIsSpecial(product.getIsSpecial());
		productDTO.setIsBestSeller(product.getIsBestSeller());
		
		return productDTO;
	}
	
	public static Product getProductDTOToProduct(ProductDTO dto) {
		if (dto == null) {
			return null;
		}
		Product product = new Product();
		product.setId(dto.getId());
		product.setProductId(dto.getProductId());
		product.setName(dto.getName());
		product.setCode(dto.getCode());
		product.setTitle(dto.getTitle());
		product.setDescription(dto.getDescription());
		product.setBrand(dto.getBrand());
		product.setPrice(dto.getPrice());
		product.setPrimaryImageUrl(dto.getPrimaryImageUrl());
		product.setSecondaryImageUrl(dto.getSecondaryImageUrl());
		product.setCategoryCode(dto.getCategoryCode());
		product.setUnit(dto.getUnit());
		product.setActive(dto.getActive());
		product.setIsNew(dto.getIsNew());
		product.setIsFeatured(dto.getIsFeatured());
		product.setIsSpecial(dto.getIsSpecial());
		product.setIsBestSeller(dto.getIsBestSeller());
		
		return product;
	}
}
