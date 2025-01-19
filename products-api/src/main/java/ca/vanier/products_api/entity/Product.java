package ca.vanier.products_api.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// TODO: Fix it
// import lombok.Getter;
// import lombok.Setter;

import jakarta.persistence.OneToMany;

@Entity
public class Product {
    
    @Id
    @GeneratedValue
    private Long id;
    private String descr;
    private double price;

    // Assignment: Replace it by a @Entity
    private String category;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Tag> tags;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDescr() {
        return descr;
    }
    public void setDescr(String descr) {
        this.descr = descr;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public List<Tag> getTags() {
        return tags;
    }
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

}
