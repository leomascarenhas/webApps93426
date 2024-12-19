package ca.vanier.liquor_store_api.util;

import java.util.Calendar;
import java.util.Date;

import ca.vanier.liquor_store_api.entity.Customer;

public class CustomerValidator {
    
    public static boolean isCustomerValid(Customer customer) {
        return !customer.getFirstName().isEmpty() &&
            !customer.getLastName().isEmpty() &&
            customer.getDateOfBirth() != null;
    }

    public static boolean isCustomerAllowed(Customer customer)  {
        // Create Calendar object
        Calendar calendar = Calendar.getInstance();

        // Set the date of calendar to 18 years before today
        calendar.add(Calendar.YEAR, -18);

        // Use the date 18 years ago as a cutoff date which the customer must be born on
        // or after.
        Date cutoffDate = calendar.getTime();

        return !customer.getDateOfBirth().after(cutoffDate);
    }


}
