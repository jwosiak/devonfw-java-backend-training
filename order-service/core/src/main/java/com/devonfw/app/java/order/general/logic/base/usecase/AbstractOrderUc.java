package com.devonfw.app.java.order.general.logic.base.usecase;

import javax.inject.Inject;

import com.devonfw.app.java.order.general.dataaccess.api.repo.CustomerRepository;
import com.devonfw.app.java.order.general.dataaccess.api.repo.ItemRepository;
import com.devonfw.app.java.order.general.dataaccess.api.repo.OrderRepository;
import com.devonfw.app.java.order.general.logic.base.AbstractUc;

/**
 * Abstract use case for Orders, which provides access to the commonly necessary data access objects.
 */
public abstract class AbstractOrderUc extends AbstractUc {

  /** @see #getOrderRepository() */
  @Inject
  private OrderRepository orderRepository;

  @Inject
  private ItemRepository itemRepository;

  @Inject
  private CustomerRepository customerRepository;

  /**
   * @return the {@link OrderRepository} instance.
   */
  public OrderRepository getOrderRepository() {

    return this.orderRepository;
  }

  public ItemRepository getItemRepository() {

    return this.itemRepository;
  }

  public CustomerRepository getCustomerRepository() {

    return this.customerRepository;
  }

}
