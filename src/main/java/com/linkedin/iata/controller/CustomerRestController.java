package com.linkedin.iata.controller;

import com.linkedin.iata.model.Customer;
import com.linkedin.iata.repository.CustomerRepository;
import com.linkedin.iata.resource.CustomerResource;
import com.linkedin.iata.resource.CustomerResourceAssembler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@ExposesResourceFor(Customer.class)
@RequestMapping(value = "/customers", produces = "application/json")
public class CustomerRestController {

  @Autowired
  private CustomerRepository repository;

  @Autowired
  private CustomerResourceAssembler assembler;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Collection<CustomerResource>> findAll() {
    List<Customer> customers = repository.findAll();
    return new ResponseEntity<>(assembler.toResourceCollection(customers), HttpStatus.OK);
  }

  @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
  public ResponseEntity<CustomerResource> create(@RequestBody Customer customer) {
    Customer createdCustomer = repository.create(customer);
    return new ResponseEntity<>(assembler.toResource(createdCustomer), HttpStatus.CREATED);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<CustomerResource> findById(@PathVariable Long id) {
    Optional<Customer> customer = repository.findById(id);

    if (customer.isPresent()) {
      return new ResponseEntity<>(assembler.toResource(customer.get()), HttpStatus.OK);
    }
    else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    boolean wasDeleted = repository.delete(id);
    HttpStatus responseStatus = wasDeleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND;
    return new ResponseEntity<>(responseStatus);
  }
}