package com.example.spring.jpa.models;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
