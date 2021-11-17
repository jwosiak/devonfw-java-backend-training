package com.devonfw.app.java.order.general.logic.base.usecase;

import javax.inject.Inject;

import com.devonfw.app.java.order.general.dataaccess.api.repo.CustomerRepository;
import com.devonfw.app.java.order.general.logic.base.AbstractUc;

/**
 * Abstract use case for Customers, which provides access to the commonly necessary data access objects.
 */
public abstract class AbstractCustomerUc extends AbstractUc {

  /** @see #getCustomerRepository() */
  @Inject
  private CustomerRepository customerRepository;

  /**
   * @return the {@link CustomerRepository} instance.
   */
  public CustomerRepository getCustomerRepository() {

    return this.customerRepository;
  }

}
