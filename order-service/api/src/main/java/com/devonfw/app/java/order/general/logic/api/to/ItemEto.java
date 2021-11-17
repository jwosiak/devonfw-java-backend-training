package com.devonfw.app.java.order.general.logic.api.to;

import com.devonfw.app.java.order.general.common.api.Item;
import com.devonfw.module.basic.common.api.to.AbstractEto;

/**
 * @author Jakub Wosiak, Capgemini
 *
 */
public class ItemEto extends AbstractEto implements Item {

  private String name;

  private String description;

  private double price;

  @Override
  public String getName() {

    return this.name;
  }

  @Override
  public void setName(String name) {

    this.name = name;
  }

  @Override
  public String getDescription() {

    return this.description;
  }

  @Override
  public void setDescription(String description) {

    this.description = description;
  }

  @Override
  public double getPrice() {

    return this.price;
  }

  @Override
  public void setPrice(double price) {

    this.price = price;
  }

}
