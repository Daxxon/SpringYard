package com.example.customer.service;

import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;
import com.example.customer.repository.CustomerRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private final String INSERT_SQL = "INSERT INTO customers (firstName, lastName, phone, email) VALUES (?,?,?,?)";
  @Transactional
  @Override
  public void add(Customer customer) {
    jdbcTemplate.update(INSERT_SQL, customer.getFirstName(), customer.getLastName(), customer.getPhoneNumber(), customer.getEmailAddress());
  }

  private final String SELECT_BY_ID_SQL = "SELECT * FROM customers WHERE id = ?";
  @Transactional
  @Override
  public Customer getById(int id) {
    return jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, new CustomerRepositoryImpl.customerMapper(), id);
  }

  private final String SELECT_SQL = "SELECT * FROM customers";
  @Transactional
  @Override
  public List<Customer> get() {
    return jdbcTemplate.query(SELECT_SQL, new CustomerRepositoryImpl.customerMapper());
  }

  private final String UPDATE_SQL = "UPDATE customers SET firstName=?, lastName=?, phone=?, email=? where id=?";
  @Transactional
  @Override
  public void update(Customer customer) {
    jdbcTemplate.update(UPDATE_SQL, customer.getFirstName(), customer.getLastName(), customer.getPhoneNumber(), customer.getEmailAddress(), customer.getId());
  }

  private final String DELETE_SQL = "DELETE FROM customers WHERE id=?";
  @Transactional
  @Override
  public void delete(int id) {
    jdbcTemplate.update(DELETE_SQL, id);
  }

  @Transactional
  @Override
  public void add(List<Customer> customers) {
    for (Customer customer : customers) {
      customerRepository.add(customer);
    }
    // Functional programming alternative:
    //customers.stream().forEach(e -> customerRepository.add(e));
  }

//  private static class customerMapper implements RowMapper<Customer> {
//    @Override
//    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
//      Customer customer = new Customer();
//      customer.setId(rs.getInt("id"));
//      customer.setFirstName(rs.getString("firstName"));
//      customer.setLastName(rs.getString("lastName"));
//      customer.setPhoneNumber(rs.getString("phone"));
//      customer.setEmailAddress(rs.getString("email"));
//      return customer;
//    }
//  }
}