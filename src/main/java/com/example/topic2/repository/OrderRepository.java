package com.example.topic2.repository;

import com.example.topic2.entity.TacoOrder;
import com.example.topic2.security.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<TacoOrder,Long> {
    //TacoOrder save(TacoOrder order);
    List<TacoOrder> findByOwner(User owner, Pageable pageable);
}
