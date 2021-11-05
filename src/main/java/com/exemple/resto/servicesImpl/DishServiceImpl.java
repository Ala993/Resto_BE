package com.exemple.resto.servicesImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.exemple.resto.entities.Dish;
import com.exemple.resto.repositories.DishRepo;
import com.exemple.resto.services.DishService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DishServiceImpl implements DishService {
	
	@Autowired
	private DishRepo dishRepo;
	@Autowired
	private ServletContext context;
	
	@Override
	public List<Dish> getAllDishes() {
		return dishRepo.findAll();
	}

	@Override
	public Dish getDishById(Long id) throws Exception {
		return dishRepo.findById(id).orElseThrow(()-> new Exception());
	}

	@Override
	public void deleteDish(Long id) {
		dishRepo.deleteById(id);
	}

	@Override
	public Dish editDish(Dish dish) {
		return dishRepo.save(dish);
	}

	@Override
	public Dish postDish(MultipartFile file, String dish) throws JsonMappingException, JsonParseException, IOException {
		Dish di = new ObjectMapper().readValue(dish, Dish.class);
		boolean isTheir = new File(context.getRealPath("/Images/")).exists();
		if (!isTheir) {
			new File (context.getRealPath("/Images/")).mkdir();
			System.out.println("mkdir ....");
		}
		String filename = file.getOriginalFilename();
		String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
		File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
		Dish finalDish = new Dish(di.getName(), di.getDescription(), filename, di.getPrice());
		try {
			FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
			System.out.println("image saved");	
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return dishRepo.save(finalDish);
	}

	@Override
	public byte[] getImage(Long id) throws Exception {
		Dish dish = dishRepo.findById(id).orElseThrow(()-> new Exception());
		return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+dish.getImg()));
		
	}
	
	
}
