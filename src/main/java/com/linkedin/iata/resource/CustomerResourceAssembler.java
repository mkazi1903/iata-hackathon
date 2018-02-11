package com.linkedin.iata.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import com.linkedin.iata.model.Customer;

@Component
public class CustomerResourceAssembler extends ResourceAssembler<Customer, CustomerResource> {

  @Autowired
  protected EntityLinks entityLinks;

  @Override
  public CustomerResource toResource(Customer customer) {

    CustomerResource resource = new CustomerResource(customer);

    final Link selfLink = entityLinks.linkToSingleResource(customer);

    resource.add(selfLink.withSelfRel());

    return resource;
  }
}
