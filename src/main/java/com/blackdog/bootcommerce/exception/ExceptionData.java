package com.blackdog.bootcommerce.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionData {

	private Integer status;
	private String message;
	private Long timeStamp;
	
	@Override
	public String toString() {
		return "ExcectionData [status=" + status + ", message=" + message + ", timeStamp=" + timeStamp + "]";
	}
	
	
	
}
