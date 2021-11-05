package com.exemple.resto.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.exemple.resto.entities.Dish;
import com.exemple.resto.services.DishService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@CrossOrigin("*")
public class DishController {
	@Autowired
	private DishService dishService;
	
	@GetMapping("/dishes")
	public List<Dish> getAllDishes (){
		return dishService.getAllDishes();
	}
	@GetMapping("/dishes/{id}")
	public Dish getDishById (@PathVariable Long id) throws Exception {
		return dishService.getDishById(id);
	}
	@PutMapping("/dishes")
	public Dish editDish(@RequestBody Dish dish) {
		return dishService.editDish(dish);
	}
	@DeleteMapping("/dishes/{id}")
	public void deleteDish (@PathVariable Long id) {
		dishService.deleteDish(id);
	}
	@PostMapping("/dishes")
	public Dish postDish (@RequestParam("file") MultipartFile file, @RequestParam("dish") String dish) throws JsonMappingException, JsonProcessingException, IOException {
		
		return dishService.postDish(file, dish);
	}
	@GetMapping("/dishImage/{id}")
	public byte[] getImage (@PathVariable Long id) throws Exception {
		return dishService.getImage(id);
	}
}


