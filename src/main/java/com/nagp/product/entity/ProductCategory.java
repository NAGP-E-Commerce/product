package com.nagp.product.entity;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class ProductCategory {
	
	@Id
	private String id;
	
	private String name;
	
	private String superCategory;
	
	private String similarCategory;
	
	private List<Product> product;
	
}
