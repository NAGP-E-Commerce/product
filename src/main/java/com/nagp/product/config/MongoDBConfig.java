package com.nagp.product.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;

@Configuration
public class MongoDBConfig extends AbstractMongoClientConfiguration {

	@Value("${spring.data.mongodb.database}")
	private String database;

	@Value("${spring.data.mongodb.uri}")
	private String uri;
	
//	@Value("${gridFS.collection}")
	private String collection = "attachment";

	@Bean
	@Override
	public MongoClient mongoClient() {
		ConnectionString connectionString = new ConnectionString(uri);
		MongoClient mongoClient = MongoClients.create(connectionString);
		return mongoClient;
	}

	@Override
	public boolean autoIndexCreation() {
		return false;
	}

	@Override
	protected String getDatabaseName() {
		return database;
	}
	
	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
		return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter(), collection);
	}

	@Bean
	public GridFSBucket gridFSBucket() throws Exception {
		return GridFSBuckets.create(mongoClient().getDatabase(database), collection);
	}
}