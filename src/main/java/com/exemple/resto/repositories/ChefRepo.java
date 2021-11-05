package com.exemple.resto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemple.resto.entities.Chef;

public interface ChefRepo extends JpaRepository<Chef, Long> {

}
