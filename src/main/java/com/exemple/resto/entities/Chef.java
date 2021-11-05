package com.exemple.resto.entities;


import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data@NoArgsConstructor@ToString
public class Chef {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String position;
	private String img;
	private Date birthDate;
	
	public Chef(String name, String position, String img, Date birthDate) {
		super();
		this.name = name;
		this.position = position;
		this.img = img;
		this.birthDate = birthDate;
	}
	
	
	
	
	
}
