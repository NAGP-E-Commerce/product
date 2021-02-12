package com.nagp.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nagp.product.dto.ProductCategoryDTO;
import com.nagp.product.entity.ProductCategory;
import com.nagp.product.mapping.ProductCategoryMapping;
import com.nagp.product.service.ProductCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "ProductControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("api/ecommerce")
public class ProductCategoryController {
	
	@Autowired
	ProductCategoryService productCategoryService;

	@RequestMapping(path = "productcategory/{name}", method = RequestMethod.GET)
	@ApiOperation("Gets product category with name")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = ProductCategoryDTO.class)})
	public ResponseEntity<ProductCategoryDTO> getByName(@PathVariable("name") 
	@NotBlank(message = "name must not be empty") String name) {
		ProductCategory productCategory = productCategoryService.getByProductCategoryName(name);
		ProductCategoryDTO productCategoryDTODTO = ProductCategoryMapping.getProductCategoryToProductCategoryDTO(productCategory);
		return ResponseEntity.status(HttpStatus.OK).body(productCategoryDTODTO);
	}
	
	@RequestMapping(path = "productcategory", method = RequestMethod.GET)
	@ApiOperation("Gets all product category")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProductCategoryDTO.class) })
	public ResponseEntity<List<ProductCategoryDTO>> getCategories() {
		List<ProductCategory> productCategory = productCategoryService.getCategories();
		List<ProductCategoryDTO> productCategoryDTO = new ArrayList<>();
		productCategory.forEach(productCat -> {
			productCategoryDTO.add(ProductCategoryMapping.getProductCategoryToProductCategoryDTO(productCat));
		});
		return ResponseEntity.status(HttpStatus.OK).body(productCategoryDTO);
	}
	
	@RequestMapping(path = "productcategory/{name}", method = RequestMethod.DELETE)
	@ApiOperation("Delete product category with name")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = ProductCategoryDTO.class)})
	public ResponseEntity<Boolean> delateByName(@PathVariable("name") 
	@NotBlank(message = "name must not be empty") String name) {
		Boolean flag = productCategoryService.deleteProductCategory(name);
		return ResponseEntity.status(HttpStatus.OK).body(flag);
	}
	
	@RequestMapping(path = "productcategory", method = RequestMethod.POST)
	@ApiOperation("Create product category with name")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = ProductCategoryDTO.class)})
	public ResponseEntity<ProductCategoryDTO> createProductCategory(@RequestBody ProductCategoryDTO productCategoryDTO) {
		ProductCategory productCategory = ProductCategoryMapping.getProductCategoryDTOToProductCategory(productCategoryDTO);
		ProductCategory dbCopy = productCategoryService.saveProductCategory(productCategory);
		ProductCategoryDTO productCategoryDTODB = ProductCategoryMapping.getProductCategoryToProductCategoryDTO(dbCopy);
		return ResponseEntity.status(HttpStatus.OK).body(productCategoryDTODB);
	}
}
