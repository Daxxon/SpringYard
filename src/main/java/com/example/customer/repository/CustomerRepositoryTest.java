package com.example.customer.repository;

import com.example.customer.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import java.lang.Math;

import static java.lang.String.valueOf;

@RunWith(SpringRunner.class)
//@ContextConfiguration(classes={Customer.class})
@SpringBootTest
public class CustomerRepositoryTest {

  @Autowired
  CustomerRepository customerRepository;

  @Test
  public void testAddGet() {
    // Get unique names every time this test runs
    String firstName = Long.toString(System.currentTimeMillis());
    String lastName = Long.toString(System.currentTimeMillis());

    Customer customer1 = new Customer();
    customer1.setFirstName(firstName);
    customer1.setLastName(lastName);
    customer1.setPhoneNumber(valueOf((Math.random() * 10000000)));
    customer1.setEmailAddress("Bojangles37@hotmail.com");
    customerRepository.add(customer1);

    List<Customer> customers = customerRepository.get();

    Customer customer2 = findInList(customers, firstName, lastName);
    Assert.assertNotNull(customer2);

    Customer customer3 = customerRepository.getById(customer2.getId());
    Assert.assertNotNull(customer3);
    Assert.assertEquals(firstName, customer3.getFirstName());
    Assert.assertEquals(lastName, customer3.getLastName());
  }

  @Test
  public void testUpdate() {
    Customer customer1 = createTestCustomer();
    customerRepository.add(customer1);

    List<Customer> customers = customerRepository.get();

    Customer customer2 = findInList(customers, customer1.getFirstName(), customer1.getLastName());
    Assert.assertNotNull(customer2);

    String updateFirstName = Long.toString(System.currentTimeMillis());
    String updateLastName = Long.toString(System.currentTimeMillis());

    customer2.setFirstName(updateFirstName);
    customer2.setLastName(updateLastName);
    customerRepository.update(customer2);

    customers = customerRepository.get();

    Customer customer3 = findInList(customers, updateFirstName, updateLastName);
    Assert.assertNotNull(customer3);
    Assert.assertEquals(customer2.getId(), customer3.getId());
  }

  @Test
  public void testDelete() {
    Customer customer1 = createTestCustomer();
    customerRepository.add(customer1);

    List<Customer> customers = customerRepository.get();

    Customer customer2 = findInList(customers, customer1.getFirstName(), customer1.getLastName());
    Assert.assertNotNull(customer2);

    customerRepository.delete(customer2.getId());

    customers = customerRepository.get();
    Customer customer3 = findInList(customers, customer1.getFirstName(), customer1.getLastName());
    Assert.assertNull(customer3);
  }

  private Customer createTestCustomer() {
    // Get unique names every time this test runs
    String firstName = Long.toString(System.currentTimeMillis());
    String lastName = Long.toString(System.currentTimeMillis());

    Customer customer = new Customer();
//    customer.setId(id);
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
