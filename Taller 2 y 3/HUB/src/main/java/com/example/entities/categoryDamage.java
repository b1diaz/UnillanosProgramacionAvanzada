package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "categorydamage")
public class categoryDamage {
    @Id
    private int categorydamage_id;
    String name;
    String color;


    //@OneToMany(mappedBy = "categoryDamage")
    //private List<costsRestoration> costsRestoration;

    public categoryDamage() {
    }

    public categoryDamage(int categorydamage_id, String name, String color) {
        this.categorydamage_id = categorydamage_id;
        this.name = name;
        this.color = color;
    }

    public int getCategorydamage_id() {
        return categorydamage_id;
    }

    public void setCategorydamage_id(int categorydamage_id) {
        this.categorydamage_id = categorydamage_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
/*
    public List<com.example.entities.costsRestoration> getCostsRestoration() {
        return costsRestoration;
    }

    public void setCostsRestoration(List<com.example.entities.costsRestoration> costsRestoration) {
        this.costsRestoration = costsRestoration;
    }
*/
}
