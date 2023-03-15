package com.example.topic2.repository;

import com.example.topic2.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,String> {
    //List<Ingredient> findAll();
    //Optional<Ingredient> findById(String id);
    //Ingredient save(Ingredient ingredient);
}
