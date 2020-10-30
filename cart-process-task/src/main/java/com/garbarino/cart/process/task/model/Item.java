package com.garbarino.cart.process.task.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="Item", schema = "SC")
public class Item implements Serializable {

	private static final long serialVersionUID = -5911087306063398111L;

	@EmbeddedId
	private ItemPk pk;
	
	@NotNull
	@Column(name = "quantity", updatable = false, nullable = false)
	private Integer quantity;
	
	@NotNull
	@Column(name = "unit_price", updatable = true, nullable = false)
	private BigDecimal unitPrice;
	
	@NotNull
	@Column(name = "subtotal", updatable = false, nullable = false)
	private BigDecimal subtotal;
	
}
