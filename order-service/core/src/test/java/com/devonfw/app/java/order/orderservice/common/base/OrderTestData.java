package com.devonfw.app.java.order.orderservice.common.base;

import java.time.LocalDate;

import com.devonfw.app.java.order.common.api.builders.OrderEtoBuilder;
import com.devonfw.app.java.order.general.common.api.OrderStatus;

/**
 * @author Jakub Wosiak, Capgemini
 *
 */
public interface OrderTestData {

  OrderEtoBuilder order = new OrderEtoBuilder().creationDate(LocalDate.parse("2019-03-15")).price(671.10)
      .status(OrderStatus.SERVED);

  OrderEtoBuilder orderTheSameDayDifferentStatus = new OrderEtoBuilder().creationDate(LocalDate.parse("2019-03-15"))
      .price(500.).status(OrderStatus.CANCELLED);

  OrderEtoBuilder orderDifferentDayTheSameStatus = new OrderEtoBuilder().creationDate(LocalDate.parse("2019-03-16"))
      .price(700.).status(OrderStatus.SERVED);

}
