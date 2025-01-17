package ca.vanier.products_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Tag {
//TODO ID is not being auto incremented
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Tag name cannot be blank")
    @Column(nullable = false, unique = true)
    private String name;

    @Size(max = 255, message = "Description cannot exceed 255 characters")
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public Tag() {

    }

    public Tag(String name, String description, Product product) {
        this.name = name;
        this.description = description;
        this.product = product;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProduct(Product product) {
        this.product = product;
        if (product != null && !product.getTags().contains(this)) {
            product.getTags().add(this);
        }
    }

    public Product getProduct() {
        return product;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return id != null && id.equals(tag.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}


