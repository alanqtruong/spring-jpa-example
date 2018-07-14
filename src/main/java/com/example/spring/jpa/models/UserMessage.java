package com.example.spring.jpa.models;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

/**
 *  @author alanqtruong
 */
@Entity
@Table(name="USER_MESSAGES")
public class UserMessage implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long id;

	@NotEmpty
	@Column(name="message", nullable=false)
	@NotBlank(message = "Message cannot be blank")
	private String message;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



}
