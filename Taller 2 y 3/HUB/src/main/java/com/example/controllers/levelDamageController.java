package com.example.controllers;

import com.example.entities.levelDamage;
import com.example.repositories.levelDamageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class levelDamageController {
    @Autowired
    private levelDamageRepository levelDamageRepository;


    @GetMapping("/levelsDamages")
    public List<levelDamage> getList(){
        return levelDamageRepository.findAll();
    }

    @GetMapping("/levelDamage/{id}")
    public levelDamage get(@PathVariable int id){
        return levelDamageRepository.findById(id).orElse(null);
    }

    @PostMapping("/levelDamage")
    public levelDamage create(@RequestBody levelDamage levelDamage) {

        try {
            this.levelDamageRepository.save(levelDamage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return levelDamage;
    }

    @PutMapping("/levelDamage/{id}")
    public boolean update(@PathVariable int id,  @RequestBody levelDamage levelDamage) {
        levelDamage entity = this.get(id);
        if (entity != null) {
            entity.setName(levelDamage.getName());
            this.levelDamageRepository.save(entity);
            return true;
        }else{
            return false;
        }
    }

    @DeleteMapping("/levelDamage/{id}")
    public boolean delete(@PathVariable int id) {
        try {
            this.levelDamageRepository.deleteById(id);
        } catch (Exception ex) {
           return false;
        }
        return true;
    }
}
