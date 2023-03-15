package com.example.topic2.entity;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import com.example.topic2.security.User;
import javax.persistence.*;
import lombok.*;


@Data
@Entity
public class TacoOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;
    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZip;
    private String ccNumber;
    private String ccExpiration;
    @Column(name = "cc_cvv")
    private String ccCVV;
    @OneToMany(mappedBy = "tacoOrder", cascade = CascadeType.ALL)
    private List<Taco> tacos = new ArrayList<>();
    private Date placedAt;

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
        taco.setTacoOrder(this);
    }
}
