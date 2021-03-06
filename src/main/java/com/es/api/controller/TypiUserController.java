package com.es.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.es.api.dto.TypiUserListDto;
import com.es.api.model.TypiUser;
import com.es.api.service.QueryDSLService;
import com.es.api.service.TypiUserService;

@Controller
@RequestMapping("/es/api")
public class TypiUserController {

	@Autowired
	private TypiUserService typiUserService;
	@Autowired
	private QueryDSLService queryDSLService;

	@PostMapping("users")
	public ResponseEntity<TypiUserListDto> getTypiUsers() {
		TypiUserListDto users = typiUserService.getUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("firstName")
	public ResponseEntity<TypiUserListDto> findByfirstName(@RequestParam String firstName) {
		TypiUserListDto users = typiUserService.getUserByTitle(firstName);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("title")
	public ResponseEntity<List<TypiUser>> findIfTitleContains(@RequestParam String title) {
		List<TypiUser> users = queryDSLService.searchIfTitleContains(title);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
}
