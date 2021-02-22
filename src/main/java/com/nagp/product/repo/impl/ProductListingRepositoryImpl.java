package com.nagp.product.repo.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagp.product.dto.ProductDTO;
import com.nagp.product.entity.Product;
import com.nagp.product.mapping.ProductMapping;
import com.nagp.product.repo.ProductListingRepository;

@Component
public class ProductListingRepositoryImpl implements ProductListingRepository {

	@Autowired
	private ObjectMapper objectMapper;

	@Bean
	public RestHighLevelClient client() {
		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY,
				new UsernamePasswordCredentials("elastic", "2IWsOeVvLI43Olp8Mx1T8Wnq"));

		RestClientBuilder builder = RestClient
				.builder(new HttpHost("3637c5436bcd41c7b1f802d6a7b84fc4.us-central1.gcp.cloud.es.io", 9243, "https"))
				.setHttpClientConfigCallback(
						httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));

		RestHighLevelClient client = new RestHighLevelClient(builder);
		return client;
	}

	@Override
	public List<ProductDTO> findAllProductDetailsFromElastic(String url) {
		final SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("productmanage");
		final SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		searchSourceBuilder.size(40);
		searchSourceBuilder.timeout(new TimeValue(30, TimeUnit.SECONDS));
		searchRequest.source(searchSourceBuilder);
		final List<Product> productList = new ArrayList<>();
		SearchResponse searchResponse = null;
		try {
			searchResponse = client().search(searchRequest, RequestOptions.DEFAULT);
			if (searchResponse.getHits().getTotalHits().value > 0) {
				final SearchHit[] searchHit = searchResponse.getHits().getHits();
				for (final SearchHit hit : searchHit) {
					final Map<String, Object> map = hit.getSourceAsMap();
					productList.add(objectMapper.convertValue(map, Product.class));
				}
			}
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conversion(productList, url);
	}

	@Override
	public List<ProductDTO> findAllProductDataByNameFromElastic(final String productName, String url) {
		final SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("productmanage");
		final SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		if (StringUtils.isNotBlank(productName)) {
			if (!productName.matches("[0-9]+")) {
				searchSourceBuilder.query(QueryBuilders.matchQuery("name", productName));
			} else {
				searchSourceBuilder.query(QueryBuilders.matchQuery("productId", productName));
			}
		} else {
			searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		}
		searchSourceBuilder.size(40);
		searchSourceBuilder.timeout(new TimeValue(30, TimeUnit.SECONDS));
		searchRequest.source(searchSourceBuilder);
		final List<Product> productList = new ArrayList<>();

		try {
			SearchResponse searchResponse = null;
			searchResponse = client().search(searchRequest, RequestOptions.DEFAULT);
			if (searchResponse.getHits().getTotalHits().value > 0) {
				final SearchHit[] searchHit = searchResponse.getHits().getHits();
				for (final SearchHit hit : searchHit) {
					final Map<String, Object> map = hit.getSourceAsMap();
					productList.add(objectMapper.convertValue(map, Product.class));
				}
			}
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conversion(productList, url);
	}

	@Override
	public List<ProductDTO> findAllProductDataByCodeFromElastic(final String productCode, String url) {
		final SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("productmanage");
		final SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("code.keyword", productCode)));
		searchRequest.source(searchSourceBuilder);
		final List<Product> userList = new ArrayList<>();

		try {
			SearchResponse searchResponse = null;
			searchResponse = client().search(searchRequest, RequestOptions.DEFAULT);
			if (searchResponse.getHits().getTotalHits().value > 0) {
				final SearchHit[] searchHit = searchResponse.getHits().getHits();
				for (final SearchHit hit : searchHit) {
					final Map<String, Object> map = hit.getSourceAsMap();
					userList.add(objectMapper.convertValue(map, Product.class));
				}
			}
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conversion(userList, url);
	}

	@Override
	public List<ProductDTO> findAllProductDataByCategoryCodeFromElastic(final String categoryCode, String url) {
		final SearchRequest searchRequest = new SearchRequest();
		searchRequest.indices("productmanage");
		final SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder
				.query(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("categoryCode.keyword", categoryCode)));
		searchRequest.source(searchSourceBuilder);
		final List<Product> productList = new ArrayList<>();

		try {
			SearchResponse searchResponse = null;
			searchResponse = client().search(searchRequest, RequestOptions.DEFAULT);
			if (searchResponse.getHits().getTotalHits().value > 0) {
				final SearchHit[] searchHit = searchResponse.getHits().getHits();
				for (final SearchHit hit : searchHit) {
					final Map<String, Object> map = hit.getSourceAsMap();
					productList.add(objectMapper.convertValue(map, Product.class));
				}
			}
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conversion(productList, url);
	}

	private List<ProductDTO> conversion(List<Product> list, String url) {
		List<ProductDTO> productList = new ArrayList<>();
		for (Product product : list) {
			product.setId(product.getProductId());
			productList.add(ProductMapping.getProductToProductDTO(product, url));
		}
		return productList;
	}

}
