/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.controllers;

import com.example.entities.costsRestoration;
import com.example.repositories.costRestorationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Diaz
 */
@RestController
public class costRestoraionController {
     @Autowired
     private costRestorationRepository costRestorationRepository;
     
    @GetMapping("/costsRestorationRepositories")
    public List<costsRestoration> getList(){
        return costRestorationRepository.findAll();
    }
    
    @GetMapping("/costsRestoration/{id}")
    public costsRestoration get(@PathVariable int id){
        return costRestorationRepository.findById(id).orElse(null);
    }

    @PostMapping("/costsRestoration")
    public costsRestoration create(@RequestBody costsRestoration costsRestoration) {
        this.costRestorationRepository.save(costsRestoration);
        return costsRestoration;
    }

    @PutMapping("/costsRestoration/{id}")
    public boolean update(@PathVariable int id,  @RequestBody costsRestoration costsRestoration) {
        costsRestoration entity = this.get(id);
        if (entity != null) {
            entity.setCost(costsRestoration.getCost());
            this.costRestorationRepository.save(entity);
            return true;
        }else{
            return false;
        }
    }

    @DeleteMapping("/costsRestoration/{id}")
    public boolean delete(@PathVariable int id) {
        try {
            this.costRestorationRepository.deleteById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;
    } 
}
