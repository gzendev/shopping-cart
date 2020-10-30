package com.garbarino.cart.process.task.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import com.garbarino.cart.process.task.enums.CartStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude ="items")
@Entity
@Table(name="Cart", schema = "SC")
public class Cart implements Serializable {

	private static final long serialVersionUID = -1504999090129118520L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id", unique = true, nullable = false)
	private Long id;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
	
	@Column(name = "creation_date", insertable = true)
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private LocalDateTime creationDate;
	
	@Enumerated
    @Column(name = "status", columnDefinition = "smallint default 0", insertable = true)
    private CartStatus status;
	
	@OneToMany(mappedBy = "pk.cart", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private Set<Item> items = new HashSet<Item>();
	
	@PrePersist
	public void prePersist() {
		creationDate = LocalDateTime.now();
		status = CartStatus.NEW;
	}
	
}
