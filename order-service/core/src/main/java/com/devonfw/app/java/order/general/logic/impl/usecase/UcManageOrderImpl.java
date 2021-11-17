package com.devonfw.app.java.order.general.logic.impl.usecase;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devonfw.app.java.order.general.common.api.OrderStatus;
import com.devonfw.app.java.order.general.dataaccess.api.CustomerEntity;
import com.devonfw.app.java.order.general.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.general.dataaccess.api.OrderEntity;
import com.devonfw.app.java.order.general.logic.api.to.CustomerEto;
import com.devonfw.app.java.order.general.logic.api.to.ItemEto;
import com.devonfw.app.java.order.general.logic.api.to.OrderCto;
import com.devonfw.app.java.order.general.logic.api.to.OrderEto;
import com.devonfw.app.java.order.general.logic.api.usecase.UcManageOrder;
import com.devonfw.app.java.order.general.logic.base.usecase.AbstractOrderUc;

/**
 * Use case implementation for modifying and deleting Orders
 */
@Named
@Validated
@Transactional
public class UcManageOrderImpl extends AbstractOrderUc implements UcManageOrder {

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(UcManageOrderImpl.class);

  @Override
  public boolean deleteOrder(long orderId) {

    OrderEntity order = getOrderRepository().find(orderId);
    getOrderRepository().delete(order);
    LOG.debug("The order with id '{}' has been deleted.", orderId);
    return true;
  }

  @Override
  public OrderEto saveOrder(OrderEto order) {

    Objects.requireNonNull(order, "order");

    OrderEntity orderEntity = getBeanMapper().map(order, OrderEntity.class);

    // initialize, validate orderEntity here if necessary
    OrderEntity resultEntity = getOrderRepository().save(orderEntity);
    LOG.debug("Order with id '{}' has been created.", resultEntity.getId());
    return getBeanMapper().map(resultEntity, OrderEto.class);
  }

  @Override
  public OrderCto createOrder(CustomerEto owner, ItemEto position1, ItemEto position2) {

    CustomerEntity ownerEntity = getBeanMapper().map(owner, CustomerEntity.class);
    Set<ItemEntity> itemEntities = Arrays.asList(position1, position2).stream()
        .map(item -> getBeanMapper().map(item, ItemEntity.class)).collect(Collectors.toSet());
    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setOwner(ownerEntity);
    orderEntity.setOrderPositions(itemEntities);
    orderEntity.setPrice(position1.getPrice() + position2.getPrice());
    orderEntity = getOrderRepository().save(orderEntity);
    orderEntity.setStatus(OrderStatus.NEW);
    OrderCto res = new OrderCto();
    res.setOrder(getBeanMapper().map(orderEntity, OrderEto.class));
    res.setOwner(owner);
    res.setOrderPositions(new HashSet<>(Arrays.asList(position1, position2)));
    return res;
  }

}
