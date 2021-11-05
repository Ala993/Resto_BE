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

import com.exemple.resto.entities.Chef;
import com.exemple.resto.repositories.ChefRepo;
import com.exemple.resto.services.ChefService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ChefServiceImpl implements ChefService {
	
	@Autowired
	private ChefRepo chefRepo;
	@Autowired
	private ServletContext context;
	
	
	@Override
	public List<Chef> getAllChefs() {
		return chefRepo.findAll();
	}

	@Override
	public Chef getChefById(Long id) throws Exception {
		return chefRepo.findById(id).orElseThrow(()-> new Exception());
	}

	@Override
	public void deleteChef(Long id) {
		chefRepo.deleteById(id);
	}

	@Override
	public Chef editChef(Chef chef) {
		return chefRepo.save(chef);
	}

	@Override
	public Chef postChef(MultipartFile file, String chef) throws JsonMappingException, JsonProcessingException ,IOException {
		
		Chef ch = new ObjectMapper().readValue(chef, Chef.class);
		boolean isTheir = new File(context.getRealPath("/Images/")).exists();
		if (!isTheir) {
			new File (context.getRealPath("/Images/")).mkdir();
			System.out.println("mkdir ....");
		}
		
		String filename = file.getOriginalFilename();
		String newFileName = FilenameUtils.getBaseName(filename)+"."+FilenameUtils.getExtension(filename);
		
		File serverFile = new File (context.getRealPath("/Images/"+File.separator+newFileName));
		
	
		Chef finalChef = new Chef(ch.getName(), ch.getPosition(), filename, ch.getBirthDate());
		try {
			FileUtils.writeByteArrayToFile(serverFile, file.getBytes());
			System.out.println("image saved");
						
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return chefRepo.save(finalChef);
	}
	@Override
	public byte[] getImage (Long id) throws Exception {
		Chef chef = chefRepo.findById(id).orElseThrow(()-> new Exception());
		byte[] file = Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+chef.getImg()));
		
		System.out.println("this is the file in bytes "+file);
		return Files.readAllBytes(Paths.get(context.getRealPath("/Images/")+chef.getImg()));
	}
	
}
