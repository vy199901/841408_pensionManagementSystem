package com.vivek.pms.authorizationservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResourceNotFound extends RuntimeException {

	private static final long serialVersionUID = 2L;
	private String message;

}
