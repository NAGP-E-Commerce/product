package com.nagp.product.repo;

import java.util.List;

import com.nagp.product.dto.ProductDTO;

public interface ProductListingRepository {

	List<ProductDTO> findAllProductDetailsFromElastic(String url);

	List<ProductDTO> findAllProductDataByNameFromElastic(String productName, String url);

	List<ProductDTO> findAllProductDataByCodeFromElastic(String productCode, String url);

	List<ProductDTO> findAllProductDataByCategoryCodeFromElastic(String categoryCode, String url);
}
