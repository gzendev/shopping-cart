package com.garbarino.shoppingcart.ws.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.garbarino.shoppingcart.ws.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name="User", schema = "SC")
public class User implements Serializable {

	private static final long serialVersionUID = 2394343134442926653L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private Long id;
	
	@NotNull
	@Column(name = "firstname", updatable = false, nullable = false)
	private String firstname;
	
	@NotNull
	@Column(name = "lastname", updatable = false, nullable = false)
	private String lastname;
	
	@NotNull
	@Column(name = "email", updatable = false, nullable = false)
	private String email;
	
	public static UserDto fromModel(final User user) {
		return new UserDto(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail());		
	}
	
}
