package com.example.entities;

import java.util.List;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "costsrestoration")
public class costsRestoration {
    @Id
    private int costsrestoration_id;
    public String cost;

    @ManyToOne()
    @JoinColumn(name = "categorydamage_id")
    private categoryDamage categoryDamage;

    @ManyToOne()
    @JoinColumn(name = "leveldamage_id")
    private levelDamage levelDamage;

     @ManyToOne()
    @JoinColumn(name = "typepainting_id")
     private typePainting typepainting_id;
    
    //@OneToMany(mappedBy = "costsRestoration")
    // private List<typePainting> typesPaintings;
    
    public costsRestoration() {

    }

    public costsRestoration(int costsrestoration_id, String cost) {
        this.costsrestoration_id = costsrestoration_id;
        this.cost = cost;
    }

    public int getCostsrestoration_id() {
        return costsrestoration_id;
    }

    public void setCostsrestoration_id(int costsrestoration_id) {
        this.costsrestoration_id = costsrestoration_id;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public com.example.entities.categoryDamage getCategoryDamage() {
        return categoryDamage;
    }

    public void setCategoryDamage(com.example.entities.categoryDamage categoryDamage) {
        this.categoryDamage = categoryDamage;
    }

    public com.example.entities.levelDamage getLevelDamage() {
        return levelDamage;
    }

    public void setLevelDamage(com.example.entities.levelDamage levelDamage) {
        this.levelDamage = levelDamage;
    }

    public typePainting getTypepainting_id() {
        return typepainting_id;
    }

    public void setTypepainting_id(typePainting typepainting_id) {
        this.typepainting_id = typepainting_id;
    }

    

 
}
