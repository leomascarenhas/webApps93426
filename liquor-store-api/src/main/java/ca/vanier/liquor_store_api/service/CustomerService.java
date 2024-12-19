package ca.vanier.liquor_store_api.service;

import java.util.List;
import java.util.Optional;

import ca.vanier.liquor_store_api.entity.Customer;

public interface CustomerService {

    Customer save(Customer customer);

    Optional<Customer> findById(Long id);

    List<Customer> findAll();

    Customer update(Customer customer, Long id);

    void delete(Long id);

}
