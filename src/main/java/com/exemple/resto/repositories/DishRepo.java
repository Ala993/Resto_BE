package com.exemple.resto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemple.resto.entities.Dish;

public interface DishRepo extends JpaRepository<Dish, Long> {

}
