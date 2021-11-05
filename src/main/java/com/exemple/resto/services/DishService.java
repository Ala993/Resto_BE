package com.exemple.resto.services;
import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.exemple.resto.entities.Dish;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface DishService {
	public List<Dish> getAllDishes();
	public Dish getDishById(Long id) throws Exception;
	public void deleteDish(Long id) ;
	public Dish editDish(Dish dish);
	public Dish postDish(MultipartFile file,String dish) throws JsonMappingException, JsonParseException, IOException;
	public byte[] getImage (Long id) throws Exception;
}
