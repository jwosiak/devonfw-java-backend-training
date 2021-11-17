package com.devonfw.app.java.order.orderservice.common.base;

import com.devonfw.app.java.order.common.api.builders.CustomerEtoBuilder;

/**
 * @author Jakub Wosiak, Capgemini
 *
 */
public interface CustomerTestData {

  CustomerEtoBuilder travolta = new CustomerEtoBuilder().firstname("John").lastname("Travolta");

}
