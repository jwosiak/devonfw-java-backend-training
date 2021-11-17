package com.devonfw.app.java.order.general.service.impl.rest;

import java.util.Arrays;
import java.util.HashSet;

import com.devonfw.app.java.order.general.logic.api.to.OrderCto;
import com.devonfw.app.java.order.orderservice.common.base.CustomerTestData;
import com.devonfw.app.java.order.orderservice.common.base.ItemTestData;
import com.devonfw.app.java.order.orderservice.common.base.OrderTestData;

/**
 * @author Jakub Wosiak, Capgemini
 *
 */
public class OrderServiceRestTestHelper {

  /**
   * @return
   */
  public OrderCto createDummyOrderCto() {

    OrderCto orderCto = new OrderCto();

    orderCto.setOrder(OrderTestData.order.createNew());
    orderCto.setOwner(CustomerTestData.travolta.createNew());
    orderCto.setOrderPositions(
        new HashSet<>(Arrays.asList(ItemTestData.bolognese.createNew(), ItemTestData.carbonara.createNew())));

    return orderCto;
  }

}
