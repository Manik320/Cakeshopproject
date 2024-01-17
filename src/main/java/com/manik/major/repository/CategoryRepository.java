package com.manik.major.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manik.major.model.Category;



public interface CategoryRepository extends JpaRepository<Category, Integer>  {

}
