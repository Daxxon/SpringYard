package com.example.customer.model;

import org.springframework.context.annotation.Configuration;

@Configuration
public class Customer {
  private int id;
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String emailAddress;

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhoneNumber() {
    return this.phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getEmailAddress() {
    return this.emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String toString() {
    return ("FN: " + this.getFirstName() + ", LN: " + this.getLastName() + ", PHONE: " + this.getPhoneNumber() + ", EMAIL: " + this.getEmailAddress());
  }
}
