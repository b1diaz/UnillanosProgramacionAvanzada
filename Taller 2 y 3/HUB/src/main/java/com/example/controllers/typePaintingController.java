package com.example.controllers;

import com.example.entities.typePainting;
import com.example.repositories.typePaintingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080") //en su defecto *
@RestController
public class typePaintingController {
    @Autowired
    private typePaintingRepository typePaintingRepository;

    @GetMapping("/typesPaintings")
    public List<typePainting> getList(){
        return typePaintingRepository.findAll();
    }

    @GetMapping("/typePainting/{id}")
    public typePainting get(@PathVariable int id){
        return typePaintingRepository.findById(id).orElse(null);
    }

    @PostMapping("/typePainting")
    public typePainting create(@RequestBody typePainting typePainting) {
        this.typePaintingRepository.save(typePainting);
        return typePainting;
    }

    @PutMapping("/typePainting/{id}")
    public boolean update(@PathVariable int id,  @RequestBody typePainting typePainting) {
        typePainting entity = this.get(id);
        if (entity != null) {
            entity.setName(typePainting.getName());
            this.typePaintingRepository.save(entity);
            return true;
        }else{
            return false;
        }
    }

    @DeleteMapping("/typePainting/{id}")
    public boolean delete(@PathVariable int id) {
        try {
            this.typePaintingRepository.deleteById(id);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

}
