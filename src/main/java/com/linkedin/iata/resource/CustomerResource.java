package com.linkedin.iata.resource;

import com.linkedin.iata.model.Customer;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerResource extends ResourceSupport {

  private final long id;
  private String firstName;
  private String lastName;
  private String email;
  private String mobile;

  public CustomerResource(Customer customer) {
    id = customer.getId();
    firstName = customer.getFirstName();
    lastName = customer.getLastName();
    email = customer.getEmail();
    mobile = customer.getMobile();
  }

  @JsonProperty("id")
  public Long getResourceId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getMobile() {
    return mobile;
  }

  public String getEmail() {
    return email;
  }
}
