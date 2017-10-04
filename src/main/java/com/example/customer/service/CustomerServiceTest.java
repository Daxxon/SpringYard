package com.example.customer.service;

import com.example.customer.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.dao.DataIntegrityViolationException;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

//import static com.example.customer.common.customerUtils.createTestCustomer;
//import static com.example.customer.common.CustomerUtils.findInList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

  @Autowired
  CustomerService customerService;

  @Test
  public void testAddGet() {
    // Get unique names every time this test runs
    String firstName = Long.toString(System.currentTimeMillis());
    String lastName = Long.toString(System.currentTimeMillis());

    Customer customer1 = new Customer();
//    customer1.setId(id);
    customer1.setFirstName(firstName);
    customer1.setLastName(lastName);
    customer1.setPhoneNumber(valueOf((Math.random() * 10000000)));
    customer1.setEmailAddress("Bojangles37@hotmail.com");
    customerService.add(customer1);

    List<Customer> customers = customerService.get();

    Customer customer2 = findInList(customers, firstName, lastName);
    Assert.assertNotNull(customer2);

    Customer customer3 = customerService.getById(customer2.getId());
    Assert.assertNotNull(customer3);
    Assert.assertEquals(firstName, customer3.getFirstName());
    Assert.assertEquals(lastName, customer3.getLastName());
  }

  @Test
  public void testUpdate() {
    Customer customer1 = createTestCustomer();
    customerService.add(customer1);

    List<Customer> customers = customerService.get();

    Customer customer2 = findInList(customers, customer1.getFirstName(), customer1.getLastName());
    Assert.assertNotNull(customer2);

    String updateFirstName = Long.toString(System.currentTimeMillis());
    String updateLastName = Long.toString(System.currentTimeMillis());

    customer2.setFirstName(updateFirstName);
    customer2.setLastName(updateLastName);
    customerService.update(customer2);

    customers = customerService.get();

    Customer customer3 = findInList(customers, updateFirstName, updateLastName);
    Assert.assertNotNull(customer3);
    Assert.assertEquals(customer2.getId(), customer3.getId());
  }

  @Test
  public void testDelete() {
    Customer customer1 = createTestCustomer();
    customerService.add(customer1);

    List<Customer> customers = customerService.get();

    Customer customer2 = findInList(customers, customer1.getFirstName(), customer1.getLastName());
    Assert.assertNotNull(customer2);

    customerService.delete(customer2.getId());

    customers = customerService.get();
    Customer customer3 = findInList(customers, customer1.getFirstName(), customer1.getLastName());
    Assert.assertNull(customer3);
  }

  private Customer createTestCustomer() {
    // Get unique names every time this test runs
    String firstName = Long.toString(System.currentTimeMillis());
    String lastName = Long.toString(System.currentTimeMillis());

    Customer customer = new Customer();
    customer.setFirstName(firstName);
    customer.setLastName(lastName);
    customer.setPhoneNumber("3173173131");
    customer.setEmailAddress("Bojangles37@hotmail.com");
    System.out.println(customer.toString());
    return customer;
  }

  private Customer findInList(List<Customer> customers, String first, String last) {
    // Find the new customer in the list
    for (Customer customer : customers) {
      if (customer.getFirstName().equals(first) && customer.getLastName().equals(last)) {
        return customer;
      }
    }
    return null;
  }
}