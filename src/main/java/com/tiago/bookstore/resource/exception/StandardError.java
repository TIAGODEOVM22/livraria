package com.tiago.bookstore.resource.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandardError {
	
	private long timestamp;
	private Integer status;
	private String error;

}
