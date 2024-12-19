package ca.vanier.liquor_store_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.vanier.liquor_store_api.entity.Customer;
import ca.vanier.liquor_store_api.service.CustomerService;



@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")    
    public Customer saveCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @PutMapping("/update")
    public Customer updateCustomer(@RequestBody Customer customer) {
        return customerService.update(customer, null);
    }

    @GetMapping("/{id}")
    public Customer getMethodName(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);

        // if (customer.isPresent()) {
        //     return customer.get();
        // }
        // else {
        //     return null;
        // }
        // is the same as...
        return customer.isPresent()
            ? customer.get()
            : null;
    }
    
    @DeleteMapping("/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerService.delete(id);
        return "ok";
    }

    @GetMapping("/list")
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }
    

}
