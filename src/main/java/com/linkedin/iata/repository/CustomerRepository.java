package com.linkedin.iata.repository;

import org.springframework.stereotype.Repository;

import com.linkedin.iata.model.Customer;

@Repository
public class CustomerRepository extends InMemoryRepository<Customer> {

}
