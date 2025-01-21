package ca.vanier.products_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.vanier.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByName(String name);
}