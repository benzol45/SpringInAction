package com.example.topic2.entity;

import javax.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder
@Entity
public class Ingredient {
    @Id
    private String id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}