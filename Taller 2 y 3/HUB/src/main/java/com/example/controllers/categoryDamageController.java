package com.example.controllers;

import com.example.entities.categoryDamage;
import com.example.repositories.categoryDamageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class categoryDamageController {

    @Autowired
    private categoryDamageRepository categoryDamageRepository;

    @GetMapping("/categoriesDamages")
    public List<categoryDamage> getList(){
        return categoryDamageRepository.findAll();
    }

    @GetMapping("/categoryDamage/{id}")
    public categoryDamage get(@PathVariable int id){
        return categoryDamageRepository.findById(id).orElse(null);
    }

    @PostMapping("/categoryDamage")
    public categoryDamage create(@RequestBody categoryDamage categoryDamage) {
        this.categoryDamageRepository.save(categoryDamage);
        return categoryDamage;
    }

    @PutMapping("/categoryDamage/{id}")
    public boolean update(@PathVariable int id,  @RequestBody categoryDamage categoryDamage) {
        categoryDamage entity = this.get(id);
        if (entity != null) {
            entity.setName(categoryDamage.getName());
            this.categoryDamageRepository.save(entity);
            return true;
        }else{
            return false;
        }
    }

    @DeleteMapping("/categoryDamage/{id}")
    public boolean delete(@PathVariable int id) {
        try {
            this.categoryDamageRepository.deleteById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

}
