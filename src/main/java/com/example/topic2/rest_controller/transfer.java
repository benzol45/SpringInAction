package com.example.topic2.rest_controller;

import com.example.topic2.entity.Taco;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping(value = "/proxy", produces = "application/json")
public class transfer {
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/{id}")
    public ResponseEntity<Taco> getTacoOverAPI(@PathVariable("id") long id) {
        ResponseEntity<Taco> tacoResponseEntity = null;
        try {
            tacoResponseEntity = restTemplate.getForEntity("http://localhost:8081/api/taco/{id}", Taco.class, id);
            return ResponseEntity.ok(tacoResponseEntity.getBody());

        } catch (HttpClientErrorException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Taco> createTacoOverAPI(@RequestBody Taco taco) {
        ResponseEntity<Taco> tacoResponseEntity = null;
        try {
            tacoResponseEntity = restTemplate.postForEntity("http://localhost:8081/api/taco", taco, Taco.class);
            return ResponseEntity.ok(tacoResponseEntity.getBody());

        } catch (HttpClientErrorException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public void replaceTacoOverAPI(@PathVariable("id") long id, @RequestBody Taco taco) {
        restTemplate.put("http://localhost:8081/api/taco/{id}", taco, id);
    }

    @DeleteMapping("/{id}")
    public void deleteTacoOverIp(@PathVariable("id") long id) {
        restTemplate.delete("http://localhost:8081/api/taco/{id}",id);
    }
}
