package com.geekylikes.app.controllers;

import com.geekylikes.app.models.Geekout;
import com.geekylikes.app.repositories.GeekoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GeekoutController {
    @Autowired
    GeekoutRepository repository;

    @GetMapping
    private List<Geekout> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Geekout> createOne(@RequestBody Geekout geekout) {
        return new ResponseEntity<>(repository.save(geekout), HttpStatus.CREATED);
    }
}
