package com.nagp.product.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nagp.product.dto.ProductDTO;
import com.nagp.product.entity.Product;
import com.nagp.product.mapping.ProductMapping;
import com.nagp.product.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "ProductControllerAPI", produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping("/product")
@Validated
public class ProductController {
	Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	ProductService productService;

	@Value("${inventory.service.url}")
	private String INVENTORY_SERVICE_URL;

	@RequestMapping(path = "/test", method = RequestMethod.GET)
	@ApiOperation("Test Product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = String.class) })
	public String test() {
		return "test product service 2";
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	@ApiOperation("Gets product with specific id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProductDTO.class) })
	public ResponseEntity<ProductDTO> getById(
			@PathVariable("id") @NotBlank(message = "id must not be empty") String id) {
		Product product = productService.getByProductId(id);
		ProductDTO productDTO = ProductMapping.getProductToProductDTO(product, INVENTORY_SERVICE_URL);
		return ResponseEntity.status(HttpStatus.OK).body(productDTO);
	}

	@RequestMapping(path = "/all", method = RequestMethod.GET)
	@ApiOperation("Get All product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProductDTO.class) })
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		List<Product> products = productService.findAll();
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for (Product product : products) {
			productDTOs.add(ProductMapping.getProductToProductDTO(product, INVENTORY_SERVICE_URL));
		}
		return ResponseEntity.status(HttpStatus.OK).body(productDTOs);
	}

	@RequestMapping(path = "/code/{code}", method = RequestMethod.GET)
	@ApiOperation("Gets product by code")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProductDTO.class) })
	public ResponseEntity<ProductDTO> getByCode(
			@PathVariable("code") @NotBlank(message = "code must not be empty") String code) {
		Product product = productService.findProductByCode(code);
		ProductDTO productDTO = ProductMapping.getProductToProductDTO(product, INVENTORY_SERVICE_URL);
		return ResponseEntity.status(HttpStatus.OK).body(productDTO);
	}

	@RequestMapping(path = "/new", method = RequestMethod.GET)
	@ApiOperation("Get New product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProductDTO.class) })
	public ResponseEntity<List<ProductDTO>> getNewProducts() {
		List<Product> products = productService.findAllNewProducts();
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for (Product product : products) {
			productDTOs.add(ProductMapping.getProductToProductDTO(product, INVENTORY_SERVICE_URL));
		}
		return ResponseEntity.status(HttpStatus.OK).body(productDTOs);
	}

	@RequestMapping(path = "/special", method = RequestMethod.GET)
	@ApiOperation("Get Special product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProductDTO.class) })
	public ResponseEntity<List<ProductDTO>> getSpecialProducts() {
		List<Product> products = productService.findAllSpecialProducts();
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for (Product product : products) {
			productDTOs.add(ProductMapping.getProductToProductDTO(product, INVENTORY_SERVICE_URL));
		}
		return ResponseEntity.status(HttpStatus.OK).body(productDTOs);
	}

	@RequestMapping(path = "/bestseller", method = RequestMethod.GET)
	@ApiOperation("Get BestSeller product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProductDTO.class) })
	public ResponseEntity<List<ProductDTO>> getBestSellerProducts() {
		List<Product> products = productService.findAllBestSellerProducts();
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for (Product product : products) {
			productDTOs.add(ProductMapping.getProductToProductDTO(product, INVENTORY_SERVICE_URL));
		}
		return ResponseEntity.status(HttpStatus.OK).body(productDTOs);
	}

	@RequestMapping(path = "/featured", method = RequestMethod.GET)
	@ApiOperation("Get Featured product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProductDTO.class) })
	public ResponseEntity<List<ProductDTO>> getFeaturedProducts() {
		List<Product> products = productService.findAllFeaturedProducts();
		List<ProductDTO> productDTOs = new ArrayList<ProductDTO>();
		for (Product product : products) {
			productDTOs.add(ProductMapping.getProductToProductDTO(product, INVENTORY_SERVICE_URL));
		}
		return ResponseEntity.status(HttpStatus.OK).body(productDTOs);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ApiOperation("Create product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Product.class) })
	public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
		Product product = ProductMapping.getProductDTOToProduct(productDTO);
		Product dbCopy = productService.saveProduct(product);
		productDTO = ProductMapping.getProductToProductDTO(dbCopy, INVENTORY_SERVICE_URL);
		return ResponseEntity.status(HttpStatus.OK).body(productDTO);

	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	@ApiOperation("Update product")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = Product.class) })
	public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) {
		Product product = ProductMapping.getProductDTOToProduct(productDTO);
		Product dbCopy = productService.saveProduct(product);
		productDTO = ProductMapping.getProductToProductDTO(dbCopy, INVENTORY_SERVICE_URL);
		return ResponseEntity.status(HttpStatus.OK).body(productDTO);

	}

	@RequestMapping(path = "product/{id}", method = RequestMethod.DELETE)
	@ApiOperation("Delete product with specific id")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK", response = ProductDTO.class) })
	public ResponseEntity<Boolean> deleteProductById(
			@PathVariable("id") @NotBlank(message = "id must not be empty") String id) {
		Boolean flag = productService.deleteProduct(id);
		return ResponseEntity.status(HttpStatus.OK).body(flag);
	}
}
