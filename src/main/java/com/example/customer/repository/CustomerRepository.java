package com.example.customer.repository;

import java.util.List;
import com.example.customer.model.Customer;

public interface CustomerRepository {
  void add(Customer customer);
  Customer getById(int id);
  List<Customer> get();
  void update(Customer customer);
  void delete(int id);
}
