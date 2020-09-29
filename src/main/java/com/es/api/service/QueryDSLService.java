package com.es.api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;

import com.es.api.model.TypiUser;

@Service
public class QueryDSLService {
	@Autowired
	private ElasticsearchRestTemplate elasticsearchRestTemplate;

	public List<TypiUser> searchIfTitleContains(String data) {
		String regexp = ".*" + data + ".*";
		Query searchQuery = new NativeSearchQueryBuilder().withFilter(QueryBuilders.regexpQuery("title", regexp))
				.build();
		SearchHits<TypiUser> typiUsers = elasticsearchRestTemplate.search(searchQuery, TypiUser.class,
				IndexCoordinates.of("typicode"));
		List<TypiUser> typiUserList = typiUsers.stream().map(hit -> hit.getContent()).collect(Collectors.toList());
		return typiUserList;
	}
}
