package ca.vanier.products_api.entity;

import jakarta.annotation.Generated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

public class Tag {

    @Id
    @GeneratedValue
    private Long id;
    private String description;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    } 
}
