package com.co.algomoko.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
	U1("ROLE_USER"),
	U0("ROLE_ADMIN");
	
	private String value;
}
