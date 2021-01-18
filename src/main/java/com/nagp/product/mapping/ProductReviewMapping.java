package com.nagp.product.mapping;

import com.nagp.product.dto.ProductReviewDTO;
import com.nagp.product.entity.ProductReview;

public class ProductReviewMapping {

	public static ProductReviewDTO getProductReviewToProductReviewDTO(ProductReview productReview) {
		if (productReview == null || productReview.getId() == null) {
			return null;
		}
		
		ProductReviewDTO productReviewDTO =new ProductReviewDTO();
		productReviewDTO.setId(productReview.getId());
		productReviewDTO.setDescription(productReview.getDescription());
		productReviewDTO.setRating(productReview.getRating());
		productReviewDTO.setSubmittedBy(productReview.getSubmittedBy());
		return productReviewDTO;
	}
	
	public static ProductReview getProductReviewDTOToProductReview(ProductReviewDTO dto) {
		if (dto == null) {
			return null;
		}
		
		ProductReview productReview = new ProductReview();
		productReview.setId(dto.getId());
		productReview.setDescription(dto.getDescription());
		productReview.setRating(dto.getRating());
		productReview.setSubmittedBy(dto.getSubmittedBy());
		return productReview;
	}
}
