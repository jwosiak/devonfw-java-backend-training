package com.devonfw.app.java.order.general.dataaccess.api.repo;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.devonfw.app.java.order.general.common.api.Order;
import com.devonfw.app.java.order.general.common.api.OrderStatus;
import com.devonfw.app.java.order.general.dataaccess.api.CustomerEntity;
import com.devonfw.app.java.order.general.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.general.dataaccess.api.OrderEntity;
import com.devonfw.app.java.order.general.logic.api.to.OrderSearchCriteriaTo;
import com.devonfw.module.test.common.base.ComponentTest;

/**
 * @author Jakub Wosiak, Capgemini
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class OrderRepositoryTest extends ComponentTest {

  @Inject
  ItemRepository itemRepository;

  @Inject
  OrderRepository orderRepository;

  @Inject
  CustomerRepository customerRepository;

  @Override
  protected void doTearDown() {

    super.doTearDown();
    this.orderRepository.deleteAll();
    this.itemRepository.deleteAll();
    this.customerRepository.deleteAll();
  }

  @Before
  public void prepareData() {

    CustomerEntity owner = new CustomerEntity();
    owner.setFirstname("John");
    owner.setLastname("Travolta");

    OrderEntity order = new OrderEntity();
    order.setCreationDate(LocalDate.parse("2019-03-15"));
    order.setPrice(671.10);
    order.setStatus(OrderStatus.SERVED);
    order.setOwner(owner);

    OrderEntity orderTheSameDayDifferentStatus = new OrderEntity();
    orderTheSameDayDifferentStatus.setCreationDate(LocalDate.parse("2019-03-15"));
    orderTheSameDayDifferentStatus.setPrice(500.);
    orderTheSameDayDifferentStatus.setStatus(OrderStatus.CANCELLED);
    orderTheSameDayDifferentStatus.setOwner(owner);

    OrderEntity orderDifferentDayTheSameStatus = new OrderEntity();
    orderDifferentDayTheSameStatus.setCreationDate(LocalDate.parse("2019-03-16"));
    orderDifferentDayTheSameStatus.setPrice(700.);
    orderDifferentDayTheSameStatus.setStatus(OrderStatus.SERVED);
    orderDifferentDayTheSameStatus.setOwner(owner);

    Set<OrderEntity> orders = new HashSet<>(
        Arrays.asList(order, orderTheSameDayDifferentStatus, orderDifferentDayTheSameStatus));

    this.customerRepository.save(owner);
    this.orderRepository.saveAll(orders);
    owner.setOrders(orders);
    this.customerRepository.save(owner);

    ItemEntity bolognese = new ItemEntity();
    bolognese.setDescription("Italy");
    bolognese.setName("spaghetti bolognese");
    bolognese.setPrice(250);

    ItemEntity carbonara = new ItemEntity();
    carbonara.setDescription("Italy");
    carbonara.setName("spaghetti carbonara");
    carbonara.setPrice(270);

    this.itemRepository.saveAll(Arrays.asList(bolognese, carbonara));
  }

  @Test
  public void findOrdersWithSpecificDayAndStatus() {

    // given
    Pageable pageable = PageRequest.of(0, 20);
    OrderSearchCriteriaTo criteria = new OrderSearchCriteriaTo();
    criteria.setPageable(pageable);
    criteria.setCreationDate(LocalDate.parse("2019-03-15"));
    criteria.setStatus(OrderStatus.SERVED);

    // when
    List<OrderEntity> foundItems = this.orderRepository.findByCriteria(criteria).getContent();

    // then
    assertThat(foundItems).hasSize(1);
    Order foundItem = foundItems.get(0);
    assertThat(foundItem.getCreationDate()).isEqualTo(LocalDate.parse("2019-03-15"));
    assertThat(foundItem.getStatus()).isEqualTo(OrderStatus.SERVED);
    assertThat(foundItem.getPrice()).isEqualTo(671.10);
  }

  @Test
  public void createOrderWithTwoPositionsAndOwner() {

    // given
    CustomerEntity owner = this.customerRepository.findAll().get(0);
    Set<ItemEntity> orderPositions = new HashSet<>(this.itemRepository.findAll());
    OrderEntity order = new OrderEntity();
    order.setCreationDate(LocalDate.parse("2021-11-16"));
    order.setStatus(OrderStatus.SERVED);
    order.setOwner(owner);
    order.setPrice(520.);
    order.setOrderPositions(orderPositions);

    // when
    this.orderRepository.save(order);

    // then
    Optional<OrderEntity> persistedOrder = this.orderRepository.findById(order.getId());
    assertThat(persistedOrder).isNotEmpty();
    assertThat(persistedOrder.get().getId()).isEqualTo(order.getId());
  }

}
