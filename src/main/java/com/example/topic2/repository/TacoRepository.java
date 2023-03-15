package com.example.topic2.repository;

import com.example.topic2.entity.Taco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacoRepository extends JpaRepository<Taco, Long> {
}
