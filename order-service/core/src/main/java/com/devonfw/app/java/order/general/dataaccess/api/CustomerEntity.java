package com.devonfw.app.java.order.general.dataaccess.api;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.devonfw.app.java.order.general.common.api.Customer;

/**
 * @author Jakub Wosiak, Capgemini
 */
@Entity(name = "Customer")
public class CustomerEntity extends ApplicationPersistenceEntity implements Customer {

  private String firstname;

  private String lastname;

  private Set<OrderEntity> orders;

  private static final long serialVersionUID = 1L;

  @Override
  public String getFirstname() {

    return this.firstname;
  }

  @Override
  public void setFirstname(String firstname) {

    this.firstname = firstname;
  }

  @Override
  public String getLastname() {

    return this.lastname;
  }

  @Override
  public void setLastname(String lastname) {

    this.lastname = lastname;
  }

  @OneToMany(mappedBy = "owner")
  public Set<OrderEntity> getOrders() {

    return this.orders;
  }

  public void setOrders(Set<OrderEntity> orders) {

    this.orders = orders;
  }

}
