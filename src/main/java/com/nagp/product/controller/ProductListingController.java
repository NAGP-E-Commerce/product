package com.nagp.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagp.product.dto.ProductDTO;
import com.nagp.product.service.ProductService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/plp")
public class ProductListingController {

	@Autowired
	private ProductService productService;

	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getAllProduct() {
		return productService.getAllESProductInfo();
	}

	@GetMapping(value = "/name/{productName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getProductByName(@PathVariable String productName) {
		return productService.getESProductDataByName(productName);
	}

	@GetMapping(value = "/code/{productCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getProductByCode(@PathVariable String productCode) {
		return productService.getESProductDataByCode(productCode);
	}

	@GetMapping(value = "/ccode/{categoryCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getProductByCategory(@PathVariable String categoryCode) {
		return productService.getESProductDataByCategoryCode(categoryCode);
	}

}
