package com.devonfw.app.java.order.orderservice.service.impl.rest;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.inject.Named;

import com.devonfw.app.java.order.general.common.api.security.ApplicationAccessControlConfig;
import com.devonfw.app.java.order.general.logic.api.to.ItemEto;
import com.devonfw.app.java.order.general.logic.api.to.OrderCto;
import com.devonfw.app.java.order.orderservice.logic.api.Orderservice;
import com.devonfw.app.java.order.orderservice.service.api.rest.OrderserviceRestService;

/**
 * @author Jakub Wosiak, Capgemini
 *
 */
@Named("OrderserviceRestService")
public class OrderserviceRestServiceImpl implements OrderserviceRestService {

  @Inject
  private Orderservice orderservice;

  @Override
  public Set<ItemEto> findItemByName(String name) {

    return new HashSet<>(this.orderservice.findItemsOrderedByName(name).getContent());
  }

  @Override
  @RolesAllowed({ ApplicationAccessControlConfig.PERMISSION_ADD_ORDER })
  public OrderCto saveOrder(OrderCto order) {

    return this.orderservice.saveOrder(order);
  }

}
