package com.devonfw.app.java.order.orderservice.logic.api;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.Page;

import com.devonfw.app.java.order.general.common.api.OrderStatus;
import com.devonfw.app.java.order.general.logic.api.to.CustomerEto;
import com.devonfw.app.java.order.general.logic.api.to.CustomerSearchCriteriaTo;
import com.devonfw.app.java.order.general.logic.api.to.ItemEto;
import com.devonfw.app.java.order.general.logic.api.to.OrderCto;
import com.devonfw.app.java.order.general.logic.api.to.OrderEto;
import com.devonfw.app.java.order.general.logic.api.to.OrderSearchCriteriaTo;
import com.devonfw.app.java.order.general.logic.api.usecase.UcFindCustomer;
import com.devonfw.app.java.order.general.logic.api.usecase.UcFindOrder;
import com.devonfw.app.java.order.general.logic.api.usecase.UcManageCustomer;
import com.devonfw.app.java.order.general.logic.api.usecase.UcManageOrder;
import com.devonfw.app.java.order.general.logic.base.AbstractComponentFacade;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindItem;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageItem;

/**
 * @author Jakub Wosiak, Capgemini
 *
 */
@Named
public class OrderserviceImpl extends AbstractComponentFacade implements Orderservice {

  @Inject
  private UcFindItem ucFindItem;

  @Inject
  private UcManageItem ucManageItem;

  @Inject
  private UcFindOrder ucFindOrder;

  @Inject
  private UcManageOrder ucManageOrder;

  @Inject
  private UcFindCustomer ucFindCustomer;

  @Inject
  private UcManageCustomer ucManageCustomer;

  @Override
  public ItemEto findItem(long id) {

    return this.ucFindItem.findItem(id);
  }

  @Override
  public Page<ItemEto> findItems(ItemSearchCriteriaTo criteria) {

    return this.ucFindItem.findItems(criteria);
  }

  @Override
  public boolean deleteItem(long itemId) {

    return this.ucManageItem.deleteItem(itemId);
  }

  @Override
  public ItemEto saveItem(ItemEto item) {

    return this.ucManageItem.saveItem(item);
  }

  @Override
  public boolean deleteOrder(long orderId) {

    return this.ucManageOrder.deleteOrder(orderId);
  }

  @Override
  public OrderEto saveOrder(OrderEto order) {

    return this.ucManageOrder.saveOrder(order);
  }

  @Override
  public OrderCto createOrder(CustomerEto owner, ItemEto position1, ItemEto position2) {

    return this.ucManageOrder.createOrder(owner, position1, position2);
  }

  @Override
  public OrderEto findOrder(long id) {

    return this.ucFindOrder.findOrder(id);
  }

  @Override
  public Page<OrderEto> findOrders(OrderSearchCriteriaTo criteria) {

    return this.ucFindOrder.findOrders(criteria);
  }

  @Override
  public Page<OrderEto> findOrders(LocalDate fromDay, OrderStatus withStatus) {

    return this.ucFindOrder.findOrders(fromDay, withStatus);
  }

  @Override
  public CustomerEto findCustomer(long id) {

    return this.ucFindCustomer.findCustomer(id);
  }

  @Override
  public Page<CustomerEto> findCustomers(CustomerSearchCriteriaTo criteria) {

    return this.ucFindCustomer.findCustomers(criteria);
  }

  @Override
  public boolean deleteCustomer(long customerId) {

    return this.ucManageCustomer.deleteCustomer(customerId);
  }

  @Override
  public CustomerEto saveCustomer(CustomerEto customer) {

    return this.ucManageCustomer.saveCustomer(customer);
  }

  @Override
  public Page<ItemEto> findItemsOrderedByName(String likeName) {

    return this.ucFindItem.findItemsOrderedByName(likeName);
  }

  @Override
  public void increasePrice(String itemName, double priceGrowth) {

    this.ucManageItem.increasePrice(itemName, priceGrowth);
  }

  @Override
  public OrderCto findOrderCto(long id) {

    return this.ucFindOrder.findOrderCto(id);
  }

  @Override
  public OrderCto saveOrder(OrderCto order) {

    return this.ucManageOrder.saveOrder(order);
  }

}
