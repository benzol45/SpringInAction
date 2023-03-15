package com.example.topic2.rest_controller;

import com.example.topic2.entity.Taco;
import com.example.topic2.service.TacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/taco", produces = "application/json")
@CrossOrigin(originPatterns = "*")
public class TacoApiController {
    private TacoService tacoService;

    @Autowired
    public TacoApiController(TacoService tacoService) {
        this.tacoService = tacoService;
    }

    @GetMapping
    public List<Taco> getLastTaco() {
        return tacoService.getLastTaco();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Taco> getTaco(@PathVariable("id") long id) {
        Optional<Taco> optionalTaco = tacoService.getById(id);
        if (optionalTaco.isPresent()) {
            return ResponseEntity.ok(optionalTaco.get());
        } else  {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Taco createTaco(@RequestBody Taco taco) {
        return tacoService.save(taco);
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    public Taco replaceTaco(@PathVariable("id") long id, @RequestBody Taco taco) {
        taco.setId(id);
        taco.setCreatedAt(tacoService.getById(id).get().getCreatedAt());

        return tacoService.save(taco);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaco(@PathVariable("id") long id) {
        tacoService.removeById(id);
    }
}
