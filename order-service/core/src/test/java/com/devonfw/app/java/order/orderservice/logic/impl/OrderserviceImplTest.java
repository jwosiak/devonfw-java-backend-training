package com.devonfw.app.java.order.orderservice.logic.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.PageRequest;

import com.devonfw.app.java.order.general.common.api.OrderStatus;
import com.devonfw.app.java.order.general.logic.api.to.CustomerEto;
import com.devonfw.app.java.order.general.logic.api.to.CustomerSearchCriteriaTo;
import com.devonfw.app.java.order.general.logic.api.to.ItemEto;
import com.devonfw.app.java.order.general.logic.api.to.OrderCto;
import com.devonfw.app.java.order.general.logic.api.to.OrderEto;
import com.devonfw.app.java.order.orderservice.common.base.CustomerTestData;
import com.devonfw.app.java.order.orderservice.common.base.ItemTestData;
import com.devonfw.app.java.order.orderservice.common.base.OrderTestData;
import com.devonfw.app.java.order.orderservice.logic.api.Orderservice;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.module.basic.common.api.query.StringSearchConfigTo;
import com.devonfw.module.test.common.base.ComponentTest;

/**
 * @author Jakub Wosiak, Capgemini
 *
 */
@Transactional
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class OrderserviceImplTest extends ComponentTest implements ItemTestData, OrderTestData, CustomerTestData {

  @Inject
  private Orderservice orderservice;

  @Before
  public void prepareData() {

    this.orderservice.saveItem(cheese.createNew());
    this.orderservice.saveItem(carbonara.createNew());
    this.orderservice.saveItem(bolognese.createNew());
    this.orderservice.saveItem(schabowy.createNew());

    this.orderservice.saveCustomer(travolta.createNew());
    CustomerEto persistedCustomer = this.orderservice.findCustomers(new CustomerSearchCriteriaTo()).getContent().get(0);

    this.orderservice.saveOrder(order.ownerId(persistedCustomer.getId()).createNew());
    this.orderservice.saveOrder(orderDifferentDayTheSameStatus.ownerId(persistedCustomer.getId()).createNew());
    this.orderservice.saveOrder(orderTheSameDayDifferentStatus.ownerId(persistedCustomer.getId()).createNew());

  }

  @Test
  public void changePriceForItemWithGivenName() {

    // when
    this.orderservice.increasePrice("kotlet schabowy", 10.1);

    // then
    ItemSearchCriteriaTo criteria = new ItemSearchCriteriaTo();
    StringSearchConfigTo config = new StringSearchConfigTo();
    config.setMatchSubstring(true);
    criteria.setName("kotlet schabowy");
    criteria.setNameOption(config);
    criteria.setPageable(PageRequest.of(0, 20));

    ItemEto item = this.orderservice.findItems(criteria).getContent().get(0);
    assertThat(item.getPrice()).isEqualTo(230.1);
  }

  @Test
  public void findLikeSpecificNameInAscendingOrder() {

    // when
    List<ItemEto> foundItems = this.orderservice.findItemsOrderedByName("ti").getContent();

    // then
    assertThat(foundItems).hasSize(2);
    assertThat(foundItems.get(0).getName()).isEqualTo("spaghetti bolognese");
    assertThat(foundItems.get(1).getName()).isEqualTo("spaghetti carbonara");
  }

  @Test
  public void findOrdersFromSpecificDayWithSpecificStatus() {

    // when
    List<OrderEto> foundItems = this.orderservice.findOrders(LocalDate.parse("2019-03-15"), OrderStatus.SERVED)
        .getContent();

    // then
    assertThat(foundItems).hasSize(1);
    assertThat(foundItems.get(0)).satisfies(item -> {
      assertThat(item.getCreationDate()).isEqualTo(LocalDate.parse("2019-03-15"));
      assertThat(item.getStatus()).isEqualTo(OrderStatus.SERVED);
      assertThat(item.getPrice()).isEqualTo(671.10);
    });
  }

  @Test
  public void createOrderWithSpecificOwnerAndTwoPositions() {

    // when
    OrderCto newOrderCto = this.orderservice.createOrder(travolta.createNew(), bolognese.createNew(),
        carbonara.createNew());
    // then
    OrderCto foundOrderCto = this.orderservice.findOrderCto(newOrderCto.getOrder().getId());
    assertThat(Arrays.asList(newOrderCto, foundOrderCto)).allSatisfy(orderCto -> {
      assertThat(orderCto.getOwner()).satisfies(owner -> {
        assertThat(owner.getFirstname()).isEqualTo("John");
        assertThat(owner.getLastname()).isEqualTo("Travolta");
      });
      assertThat(orderCto.getOrderPositions()).hasSize(2);
      assertThat(orderCto.getOrderPositions()).extracting(ItemEto::getName).contains("spaghetti bolognese",
          "spaghetti carbonara");
      assertThat(orderCto.getOrder()).satisfies(orderEto -> {
        assertThat(orderEto.getPrice()).isEqualTo(520.);
        assertThat(orderEto.getStatus()).isEqualTo(OrderStatus.NEW);
      });
    });
  }

}
