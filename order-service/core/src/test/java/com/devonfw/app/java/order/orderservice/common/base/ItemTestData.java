package com.devonfw.app.java.order.orderservice.common.base;

import com.devonfw.app.java.order.common.api.builders.ItemEtoBuilder;

/**
 * @author Jakub Wosiak, Capgemini
 *
 */
public interface ItemTestData {

  ItemEtoBuilder cheese = new ItemEtoBuilder().name("cheese").price(12.50);

  ItemEtoBuilder bolognese = new ItemEtoBuilder().description("Italy").name("spaghetti bolognese").price(250);

  ItemEtoBuilder carbonara = new ItemEtoBuilder().description("Italy").name("spaghetti carbonara").price(270);

  ItemEtoBuilder schabowy = new ItemEtoBuilder().description("Poland").name("kotlet schabowy").price(220);

}
