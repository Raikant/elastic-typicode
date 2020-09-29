package com.es.api.dto;

import java.io.Serializable;
import java.util.List;

import com.es.api.model.TypiUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TypiUserListDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 45861447382231976L;
	private List<TypiUser> typiUsers;
}
