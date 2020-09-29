package com.es.api.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName = "typicode", shards = 2)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypiUser implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3800733198423608169L;
	@Field(type = FieldType.Long)
	private Long userId;
	@Id
	@Field(type = FieldType.Long)
	private Long id;
	@Field(type = FieldType.Text)
	private String title;
	@Field(type = FieldType.Boolean)
	private boolean completed;
}
