package ca.vanier.liquor_store_api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ca.vanier.liquor_store_api.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>  {
    
}
