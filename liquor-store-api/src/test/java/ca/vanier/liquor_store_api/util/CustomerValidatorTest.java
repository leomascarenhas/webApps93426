package ca.vanier.liquor_store_api.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ca.vanier.liquor_store_api.entity.Customer;

public class CustomerValidatorTest {

    @Test
    @DisplayName("This will test....")
    void testIsCustomerAllowed() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");

        LocalDate youngDate = LocalDate.of(1986, 12, 10);
        customer.setDateOfBirth(Date.valueOf(youngDate));

        assertTrue(
            CustomerValidator.isCustomerAllowed(customer));
    }

    @Test
    void testIsCustomerNotAllowed() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");

        LocalDate youngDate = LocalDate.of(2010, 10, 10);
        customer.setDateOfBirth(Date.valueOf(youngDate));

        assertFalse(
            CustomerValidator.isCustomerAllowed(customer));
    }

    @Test
    void testIsCustomeValid() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");

        LocalDate youngDate = LocalDate.of(1986, 12, 10);
        customer.setDateOfBirth(Date.valueOf(youngDate));

        assertTrue(CustomerValidator.isCustomerValid(customer));
    }

    @Test
    void testIsCustomerNotValid() {
        Customer customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("");

        LocalDate youngDate = LocalDate.of(1986, 12, 10);
        customer.setDateOfBirth(Date.valueOf(youngDate));

        assertFalse(CustomerValidator.isCustomerValid(customer));
    }
}
