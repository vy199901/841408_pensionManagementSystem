package com.vivek.pms.processpension.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProcessPensionException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ProcessPensionException(String message) {
		super(message);
	}
}
