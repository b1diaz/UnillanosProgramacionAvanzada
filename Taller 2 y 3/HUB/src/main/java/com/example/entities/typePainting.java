package com.example.entities;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "typepainting")
public class typePainting {

    @Id
    private int typepainting_id;    
    String name;

    /*
    @OneToMany(mappedBy = "costsrestoration_id")
    private List<costsRestoration> costsRestoration;
    */
    public typePainting() {
    }

    public typePainting(int typepainting_id, String name) {
        this.typepainting_id = typepainting_id;
        this.name = name;       
    }

    public int getTypepainting_id() {
        return typepainting_id;
    }

    public void setTypepainting_id(int typepainting_id) {
        this.typepainting_id = typepainting_id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*
    public List<costsRestoration> getCostsRestoration() {
        return costsRestoration;
    }

    public void setCostsRestoration(List<costsRestoration> costsRestoration) {
        this.costsRestoration = costsRestoration;
    }
*/
    
}
