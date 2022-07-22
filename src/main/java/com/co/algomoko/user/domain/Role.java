package com.co.algomoko.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
	U1("ROLE_USER"),
	U2("ROLE_ADMIN");
	
	private String value;
}
