package com.devonfw.app.java.order.general.service.impl.rest;

import java.time.LocalDate;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.devonfw.app.java.order.SpringBootApp;
import com.devonfw.app.java.order.general.common.base.test.DbTestHelper;
import com.devonfw.app.java.order.general.dataaccess.api.repo.CustomerRepository;
import com.devonfw.app.java.order.general.logic.api.to.OrderCto;
import com.devonfw.app.java.order.general.service.base.test.RestServiceTest;
import com.devonfw.app.java.order.orderservice.logic.api.Orderservice;
import com.devonfw.app.java.order.orderservice.service.api.rest.OrderserviceRestService;

/**
 * @author Jakub Wosiak, Capgemini
 *
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { SpringBootApp.class, OrderServiceRestTestConfig.class })
public class OrderServiceRestTest extends RestServiceTest {

  @Inject
  private OrderServiceRestTestHelper helper;

  @Inject
  private OrderserviceRestService service;

  @Inject
  private DbTestHelper dbTestHelper;

  @Inject
  private Orderservice orderservice;

  @Inject
  private CustomerRepository customerRepository;

  @Override
  public void doSetUp() {

    super.doSetUp();
    this.dbTestHelper.resetDatabase();
  }

  @Override
  public void doTearDown() {

    this.service = null;
    super.doTearDown();
  }

  @Test
  public void shouldFindItemByName() {

    // given
    OrderCto order = this.helper.createDummyOrderCto();

    // when
    this.orderservice.saveOrder(order);

    // then
    assertThat(order.getOrder().getCreationDate()).isEqualTo(LocalDate.parse("2019-03-15"));
    assertThat(order.getOwner().getLastname()).isEqualTo("Travolta");
  }
}
