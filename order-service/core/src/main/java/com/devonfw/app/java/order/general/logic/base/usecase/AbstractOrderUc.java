package com.devonfw.app.java.order.general.logic.base.usecase;

import javax.inject.Inject;

import com.devonfw.app.java.order.general.dataaccess.api.repo.OrderRepository;
import com.devonfw.app.java.order.general.logic.base.AbstractUc;

/**
 * Abstract use case for Orders, which provides access to the commonly necessary data access objects.
 */
public abstract class AbstractOrderUc extends AbstractUc {

  /** @see #getOrderRepository() */
  @Inject
  private OrderRepository orderRepository;

  /**
   * @return the {@link OrderRepository} instance.
   */
  public OrderRepository getOrderRepository() {

    return this.orderRepository;
  }

}
