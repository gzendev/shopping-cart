package com.garbarino.shoppingcart.ws.dto;

import java.math.BigDecimal;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	
	@NotNull
	private Long id;
	private String description;
	private BigDecimal unitPrice;
	private Integer stock;
	@NotNull
	private Integer quantity;
	
}