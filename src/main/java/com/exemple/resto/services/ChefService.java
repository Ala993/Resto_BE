package com.exemple.resto.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.exemple.resto.entities.Chef;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

public interface ChefService {
	
	public List<Chef> getAllChefs();
	public Chef getChefById(Long id) throws Exception;
	public void deleteChef(Long id) ;
	public Chef editChef(Chef chef);
	public Chef postChef(MultipartFile file,String chef) throws JsonMappingException, JsonProcessingException, IOException;
	public byte[] getImage (Long id) throws Exception;
}
