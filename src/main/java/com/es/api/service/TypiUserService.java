package com.es.api.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.WriteRequest.RefreshPolicy;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.es.api.dto.TypiUserListDto;
import com.es.api.model.TypiUser;
import com.es.api.repository.TypiCodeRepository;

@Service
public class TypiUserService {
	@Value("${typicode.api.url}")
	private String typiCodeUrl;
	private static final String INDEX = "typicode";
	@Autowired
	private TypiCodeRepository typiCodeRepository;
	@Autowired
	private RestHighLevelClient highLevelClient;

	@Autowired
	private RestTemplate restTemplate;

	public TypiUserListDto getUsers() {
		/**
		 * Here I am fetching a URL data to fetch elastic documents, to feed a json file refer Janani Ravi
		 * tutorial.
		 */
		TypiUser[] typiUsers = restTemplate.getForObject(typiCodeUrl, TypiUser[].class);
		List<TypiUser> users = Arrays.asList(typiUsers);
		TypiUserListDto typiUsersDto = TypiUserListDto.builder().typiUsers(users).build();
		IndexRequest request = new IndexRequest(INDEX).source(users, XContentType.JSON)
				.setRefreshPolicy(RefreshPolicy.IMMEDIATE);

		try {
			IndexResponse response = highLevelClient.index(request, RequestOptions.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		}

//		typiCodeRepository.saveAll(typiUsersDto.getTypiUsers());
		return typiUsersDto;
	}

	public TypiUserListDto getUserByTitle(String title) {
		typiCodeRepository.findByTitle(title);
		return null;
	}
}
