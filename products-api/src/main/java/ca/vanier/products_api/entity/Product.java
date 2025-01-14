package ca.vanier.products_api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

// TODO: Fix it
// import lombok.Getter;
// import lombok.Setter;

@Entity
// @Getter
// @Setter
public class Product {
    
    @Id
    @GeneratedValue
    private Long id;
    private String descr;
    private double price;
    private String category;

    //private 

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
    public boolean fieldCheckOK(Product product) {
        if (product.getDescr() == null || product.getDescr().trim().isEmpty()) return false;
        if (product.getCategory() == null || product.getCategory().trim().isEmpty()) return false;
        if (product.getPrice() == 0.0) return false;
        return true;
    }
}
