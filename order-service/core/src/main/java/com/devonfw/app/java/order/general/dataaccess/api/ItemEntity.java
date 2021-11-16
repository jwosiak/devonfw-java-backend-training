package com.devonfw.app.java.order.general.dataaccess.api;

import javax.persistence.Entity;

import com.devonfw.app.java.order.general.common.api.Item;

/**
 * @author Jakub Wosiak, Capgemini
 */
@Entity(name = "Item")
public class ItemEntity extends ApplicationPersistenceEntity implements Item {

  private String description;

  private String name;

  private double Price;

  private static final long serialVersionUID = 1L;

  public String getDescription() {

    return this.description;
  }

  public void setDescription(String description) {

    this.description = description;
  }

  public String getName() {

    return this.name;
  }

  public void setName(String name) {

    this.name = name;
  }

  public double getPrice() {

    return this.Price;
  }

  public void setPrice(double price) {

    this.Price = price;
  }

}
