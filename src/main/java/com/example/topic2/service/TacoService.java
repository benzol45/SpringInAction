package com.example.topic2.service;

import com.example.topic2.entity.Ingredient;
import com.example.topic2.entity.Taco;
import com.example.topic2.entity.TacoOrder;
import com.example.topic2.repository.IngredientRepository;
import com.example.topic2.repository.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TacoService {
    private final TacoRepository tacoRepository;
    private final IngredientRepository ingredientRepository;

    @Autowired
    public TacoService(TacoRepository tacoRepository, IngredientRepository ingredientRepository) {
        this.tacoRepository = tacoRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientRepository.findAll();
    }

    public void doSomething(Taco taco, TacoOrder tacoOrder) {
        tacoOrder.addTaco(taco);
    }

    public List<Taco> getLastTaco() {
        return tacoRepository.findAll(Pageable.ofSize(10)).getContent();
    }

    public Optional<Taco> getById(long id) {
        return tacoRepository.findById(id);
    }

    public Taco save(Taco taco) {
        if (taco.getCreatedAt()==null) {
            taco.setCreatedAt(new Date());
        }

        return tacoRepository.save(taco);
    }

    public void removeById(long id) {
        try {
            tacoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {

        }
    }
}
