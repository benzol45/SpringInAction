package com.example.topic2.controller;

import com.example.topic2.entity.Ingredient;
import com.example.topic2.entity.Taco;
import com.example.topic2.entity.TacoOrder;
import com.example.topic2.security.User;
import com.example.topic2.service.TacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("tacoOrder")
public class DesignTacoController {
    private final TacoService tacoService;

    @Autowired
    public DesignTacoController(TacoService tacoService) {
        this.tacoService = tacoService;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = tacoService.getIngredientList();
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @ModelAttribute("tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    @ModelAttribute("taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping("/design")
    public String showDesignForm(Model model , @AuthenticationPrincipal User user) {
        if (user!=null) {
            model.addAttribute("username", user.getUsername());
        } else {
            model.addAttribute("username", "external user");
        }
        return "design";
    }

    @PostMapping("/design")
    public String deignForm(@Valid Taco taco, BindingResult bindingResult, @ModelAttribute @Valid TacoOrder tacoOrder) {
        if (bindingResult.hasErrors()) {
            return "design";
        }

        tacoService.doSomething(taco, tacoOrder);
        return "redirect:/orders/current";

    }

    private Iterable<Ingredient> filterByType(
            List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}
