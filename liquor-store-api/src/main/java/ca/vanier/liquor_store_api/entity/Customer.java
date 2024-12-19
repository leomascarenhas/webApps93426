package ca.vanier.liquor_store_api.entity;

import java.sql.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {
    
    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String lastName;
    // TODO: Fix this
    @Basic
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

}
