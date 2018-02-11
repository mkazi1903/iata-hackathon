package com.linkedin.iata.model;

public interface Identifiable extends org.springframework.hateoas.Identifiable<Long> {
  void setId(Long id);
}