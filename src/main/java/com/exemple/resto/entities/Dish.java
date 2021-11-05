package com.exemple.resto.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Data @NoArgsConstructor
public class Dish {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id ;
	private String name;
	private String description;
	private String img;
	private int price;
	public Dish(String name, String description, String img, int price) {
		super();
		this.name = name;
		this.description = description;
		this.img = img;
		this.price = price;
	}
	
	
}
