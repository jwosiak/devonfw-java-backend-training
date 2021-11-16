package com.devonfw.app.java.order.general.dataaccess.api;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.devonfw.app.java.order.general.common.api.Order;
import com.devonfw.app.java.order.general.common.api.OrderStatus;

/**
 * @author Jakub Wosiak, Capgemini
 */
@Entity(name = "OrderSummary")
public class OrderEntity extends ApplicationPersistenceEntity implements Order {

  private Set<ItemEntity> orderPositions;

  private double price;

  private CustomerEntity owner;

  private LocalDate creationDate;

  private OrderStatus status;

  private static final long serialVersionUID = 1L;

  @ManyToMany
  @JoinTable(name = "OrderPosition", joinColumns = @JoinColumn(name = "orderId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "itemId", referencedColumnName = "id"))
  public Set<ItemEntity> getOrderPositions() {

    return this.orderPositions;
  }

  public void setOrderPositions(Set<ItemEntity> orderPositions) {

    this.orderPositions = orderPositions;
  }

  @Override
  public double getPrice() {

    return this.price;
  }

  @Override
  public void setPrice(double price) {

    this.price = price;
  }

  @ManyToOne
  @JoinColumn(name = "ownerId")
  public CustomerEntity getOwner() {

    return this.owner;
  }

  public void setOwner(CustomerEntity owner) {

    this.owner = owner;
  }

  @Override
  public LocalDate getCreationDate() {

    return this.creationDate;
  }

  @Override
  public void setCreationDate(LocalDate creationDate) {

    this.creationDate = creationDate;
  }

  @Override
  @Enumerated(EnumType.STRING)
  public OrderStatus getStatus() {

    return this.status;
  }

  @Override
  public void setStatus(OrderStatus status) {

    this.status = status;
  }

  @Override
  @Transient
  public Long getOwnerId() {

    if (this.owner == null) {
      return null;
    }
    return this.owner.getId();
  }

  @Override
  public void setOwnerId(Long ownerId) {

    if (ownerId == null) {
      this.owner = null;
    } else {
      CustomerEntity customerEntity = new CustomerEntity();
      customerEntity.setId(ownerId);
      this.owner = customerEntity;
    }
  }

}
