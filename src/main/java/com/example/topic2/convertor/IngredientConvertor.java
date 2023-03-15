package com.example.topic2.convertor;

import com.example.topic2.entity.Ingredient;

import com.example.topic2.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;


@Component
public class IngredientConvertor implements Converter<String, Ingredient> {
    private final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientConvertor(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(@NonNull String id) {
        return ingredientRepository.findById(id).orElse(null);
    }
}
