package com.garbarino.shoppingcart.ws.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.garbarino.shoppingcart.ws.dto.ProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude ="items")
@Entity
@Table(name="Product", schema = "SC")
public class Product implements Serializable {

	private static final long serialVersionUID = 4794047030567907737L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", unique = true, nullable = false)
	private Long id;
	
	@NotNull
	@Column(name = "description", updatable = false, nullable = false)
	private String description;
		
	@NotNull
	@Column(name = "unit_price", updatable = true, nullable = false)
	private BigDecimal unitPrice;
	
	@NotNull
	@Column(name = "stock", updatable = true, nullable = false)
	private Integer stock;
	
	@OneToMany(mappedBy = "pk.product", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Item> items = new HashSet<Item>();
	
	public static ProductDto fromModel(final Product product) {
		return new ProductDto(product.getId(), product.getDescription(), product.getUnitPrice(), product.getStock(), null);		
	}
	
}
