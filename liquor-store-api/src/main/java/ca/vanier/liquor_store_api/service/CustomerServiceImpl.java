package ca.vanier.liquor_store_api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ca.vanier.liquor_store_api.entity.Customer;
import ca.vanier.liquor_store_api.repository.CustomerRepository;
import ca.vanier.liquor_store_api.util.CustomerValidator;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer save(Customer customer) {
        boolean isValid = CustomerValidator.isCustomerValid(customer);
        boolean isAllowed = CustomerValidator.isCustomerAllowed(customer);

        if (!isValid || !isAllowed) {
            return null;
        }
        
        return customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public List<Customer> findAll() {
        // TODO: Improve this... Casting?
        return (List<Customer>) customerRepository.findAll();
    }

    // TODO: Improve this
    @Override
    public Customer update(Customer customer, Long id) {
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
    
}
