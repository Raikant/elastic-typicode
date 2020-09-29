package com.es.api.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.es.api.model.TypiUser;

@Repository
public interface TypiCodeRepository extends ElasticsearchRepository<TypiUser, String> {

	List<TypiUser> findByTitle(String title);

}
