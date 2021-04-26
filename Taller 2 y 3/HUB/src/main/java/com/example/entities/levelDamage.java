package com.example.entities;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "leveldamage")
public class levelDamage {

    @Id
    private int leveldamage_id;
    String name;

   // @OneToMany(mappedBy = "levelDamage")
   // private List<costsRestoration> costsRestoration;

    public levelDamage() {
    }

    public levelDamage(int leveldamage_id, String name) {
        this.leveldamage_id = leveldamage_id;
        this.name = name;
    }

    public int getLeveldamage_id() {
        return leveldamage_id;
    }

    public void setLeveldamage_id(int leveldamage_id) {
        this.leveldamage_id = leveldamage_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
    public List<com.example.entities.costsRestoration> getCostsRestoration() {
        return costsRestoration;
    }
*/
}
