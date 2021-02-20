package com.nagp.product.mapping;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

import org.springframework.web.client.RestTemplate;

import com.nagp.product.dto.ProductDTO;
import com.nagp.product.dto.ProductStatus;
import com.nagp.product.entity.Product;
import com.nagp.stock.dto.ProductStockDTO;

public class ProductMapping {

	static RestTemplate restTemplate = new RestTemplate();

	public static ProductDTO getProductToProductDTO(Product product, String url) {

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
		productDTO.setActive(product.getActive());
		productDTO.setIsNew(product.getIsNew());
		productDTO.setIsFeatured(product.getIsFeatured());
		productDTO.setIsSpecial(product.getIsSpecial());
		productDTO.setIsBestSeller(product.getIsBestSeller());

		ProductStockDTO productStockDTO = getStockStatus(product.getProductId(), url);

		if (Objects.nonNull(productStockDTO)) {
			int avilableQuantity = productStockDTO.getQuantity() - productStockDTO.getReserve();
			productDTO.setAvailableQty(avilableQuantity);
			productDTO.setStatus(
					avilableQuantity < 2 ? (avilableQuantity == 0 ? ProductStatus.OUTOFSTOCK : ProductStatus.LOWSTOCK)
							: ProductStatus.INSTOCK);
		} else {
			productDTO.setAvailableQty(0);
			productDTO.setStatus(ProductStatus.UNAVAILABLE);
		}

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
		product.setUnit(Double.valueOf("1"));
		product.setActive(dto.getActive());
		product.setIsNew(dto.getIsNew());
		product.setIsFeatured(dto.getIsFeatured());
		product.setIsSpecial(dto.getIsSpecial());
		product.setIsBestSeller(dto.getIsBestSeller());

		return product;
	}

	private static ProductStockDTO getStockStatus(String productId, String url) {

		URI uri = null;
		try {
			uri = new URI(url + "inventory/stock/" + productId);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return restTemplate.getForObject(uri, ProductStockDTO.class);

	}
}
