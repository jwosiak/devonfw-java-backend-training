package com.devonfw.app.java.order.general.logic.impl.usecase;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.devonfw.app.java.order.general.common.api.OrderStatus;
import com.devonfw.app.java.order.general.dataaccess.api.OrderEntity;
import com.devonfw.app.java.order.general.logic.api.to.CustomerEto;
import com.devonfw.app.java.order.general.logic.api.to.ItemEto;
import com.devonfw.app.java.order.general.logic.api.to.OrderCto;
import com.devonfw.app.java.order.general.logic.api.to.OrderEto;
import com.devonfw.app.java.order.general.logic.api.to.OrderSearchCriteriaTo;
import com.devonfw.app.java.order.general.logic.api.usecase.UcFindOrder;
import com.devonfw.app.java.order.general.logic.base.usecase.AbstractOrderUc;

/**
 * Use case implementation for searching, filtering and getting Orders
 */
@Named
@Validated
@Transactional
public class UcFindOrderImpl extends AbstractOrderUc implements UcFindOrder {

  /** Logger instance. */
  private static final Logger LOG = LoggerFactory.getLogger(UcFindOrderImpl.class);

  @Override
  public OrderEto findOrder(long id) {

    LOG.debug("Get Order with id {} from database.", id);
    Optional<OrderEntity> foundEntity = getOrderRepository().findById(id);
    if (foundEntity.isPresent())
      return getBeanMapper().map(foundEntity.get(), OrderEto.class);
    else
      return null;
  }

  @Override
  public Page<OrderEto> findOrders(OrderSearchCriteriaTo criteria) {

    Page<OrderEntity> orders = getOrderRepository().findByCriteria(criteria);
    return mapPaginatedEntityList(orders, OrderEto.class);
  }

  @Override
  public Page<OrderEto> findOrders(LocalDate fromDay, OrderStatus withStatus) {

    Pageable pageable = PageRequest.of(0, 20);
    OrderSearchCriteriaTo criteria = new OrderSearchCriteriaTo();
    criteria.setPageable(pageable);
    criteria.setCreationDate(fromDay);
    criteria.setStatus(withStatus);
    return mapPaginatedEntityList(getOrderRepository().findByCriteria(criteria), OrderEto.class);
  }

  @Override
  public OrderCto findOrderCto(long id) {

    OrderEntity orderEntity = getOrderRepository().find(id);
    Set<ItemEto> orderPositions = orderEntity.getOrderPositions().stream()
        .map(pos -> getBeanMapper().map(pos, ItemEto.class)).collect(Collectors.toSet());
    OrderCto res = new OrderCto();
    res.setOrder(getBeanMapper().map(orderEntity, OrderEto.class));
    res.setOwner(getBeanMapper().map(orderEntity.getOwner(), CustomerEto.class));
    res.setOrderPositions(orderPositions);
    return res;
  }

}
