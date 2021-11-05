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

import com.exemple.resto.entities.Chef;
import com.exemple.resto.services.ChefService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@CrossOrigin("*")
public class ChefController {
	
	@Autowired
	private ChefService chefService;
	
	@GetMapping("/chefs")
	public List<Chef> getAllChefs (){
		return chefService.getAllChefs();
	}
	@GetMapping("/chefs/{id}")
	public Chef getChefById (@PathVariable Long id) throws Exception {
		return chefService.getChefById(id);
	}
	@PutMapping("/chefs")
	public Chef editChef(@RequestBody Chef chef) {
		return chefService.editChef(chef);
	}
	@DeleteMapping("/chefs/{id}")
	public void deleteChef (@PathVariable Long id) {
		chefService.deleteChef(id);
	}
	@PostMapping("/chefs")
	public Chef postChef (@RequestParam("file") MultipartFile file, @RequestParam("chef") String chef) throws JsonMappingException, JsonProcessingException, IOException {
		
		return chefService.postChef(file, chef);
	}
	@GetMapping("/image/{id}")
	public byte[] getImage (@PathVariable Long id) throws Exception {
		
		return chefService.getImage(id);
	}
}
