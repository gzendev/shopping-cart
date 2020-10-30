package com.garbarino.shoppingcart.ws.message;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorMessage {

	private int statusCode;
	private LocalDateTime timestamp;
	private String message;
	private String description;
		
}

