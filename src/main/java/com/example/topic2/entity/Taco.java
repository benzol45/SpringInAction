package com.example.topic2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.*;


import java.util.Date;
import java.util.List;

@Data
@Entity
public class Taco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank (message = "not blank")

    @Size(min = 5, max = 25, message = "Must be >=5 and <=25")
    private String name;

    @ManyToMany
    @JoinTable(name = "ingredient_ref",
            joinColumns = {@JoinColumn(name = "taco", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "ingredient", referencedColumnName = "id")
            })
    private List<Ingredient> ingredients;

    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "taco_order")
    @JsonIgnore
    private TacoOrder tacoOrder;

}