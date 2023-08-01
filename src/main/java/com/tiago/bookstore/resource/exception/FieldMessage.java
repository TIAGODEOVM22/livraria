package com.tiago.bookstore.resource.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FieldMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String fildName;
	private String message;
	

}
