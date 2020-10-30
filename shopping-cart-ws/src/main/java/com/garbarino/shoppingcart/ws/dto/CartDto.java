package com.garbarino.shoppingcart.ws.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import javax.validation.constraints.NotNull;
import com.garbarino.shoppingcart.ws.model.Cart;
import com.garbarino.shoppingcart.ws.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
	
	private Long id;
	@NotNull
	private String firstname;
	@NotNull
	private String lastname;
	@NotNull
	private String email;
	private LocalDateTime creationDate;
	private String status;
	private BigDecimal total;
	private Set<ProductDto> products;
	
	public static Cart toModel(CartDto dto) {
		return new Cart(dto.getId(), new User(null, dto.getFirstname(), dto.getLastname(), dto.getEmail()), dto.getCreationDate(), null, null);
	}
	
}