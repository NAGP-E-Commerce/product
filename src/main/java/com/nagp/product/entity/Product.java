package com.nagp.product.entity;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "product")
public class Product implements Serializable, Persistable{

	private static final long serialVersionUID = -3522880733587968745L;
	
	@Id
	private String id;
	
	private String productId;
	private String name;	
	private String code;	
	private String title;	
	private String description;	
	private String brand;
	private String price;
	private String primaryImageUrl;
	private String secondaryImageUrl;
	private String categoryCode;
	private String unit;
	private String seller;
	private Boolean active;
	private Boolean isNew;
	private Boolean isFeatured;
	private Boolean isSpecial;
	private Boolean isBestSeller;
	
	private List<ProductReview> productReview;
	
	@Override
	public boolean isNew() {
		return id == null;
	}
}
