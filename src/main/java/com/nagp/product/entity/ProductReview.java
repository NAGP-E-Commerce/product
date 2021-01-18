package com.nagp.product.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ProductReview {
	
	private String id;
	
	private String description;
	
	private String rating;
	
	private String submittedBy;
	
}
