package com.devonfw.app.java.order.general.dataaccess.api.repo;

import static org.junit.Assume.assumeThat;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.devonfw.app.java.order.general.dataaccess.api.CustomerEntity;
import com.devonfw.module.test.common.base.ComponentTest;

/**
 * @author Jakub Wosiak, Capgemini
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class CustomerRepositoryTest extends ComponentTest {

  @Inject
  CustomerRepository customerRepository;

  @Override
  protected void doTearDown() {

    super.doTearDown();
    this.customerRepository.deleteAll();
  }

  @Before
  public void prepareData() {

    CustomerEntity travolta = new CustomerEntity();
    travolta.setFirstname("John");
    travolta.setLastname("Travolta");

    CustomerEntity eastwood = new CustomerEntity();
    eastwood.setFirstname("Clint");
    eastwood.setLastname("Eastwood");

    this.customerRepository.saveAll(Arrays.asList(travolta, eastwood));
  }

  @Test
  public void deleteCustomerById() {

    // given
    List<CustomerEntity> allCustomers = this.customerRepository.findAll();
    assumeThat(2, IsEqual.equalTo(allCustomers.size()));
    Long removedCustomerId = allCustomers.get(0).getId();

    // when
    this.customerRepository.deleteById(removedCustomerId);
    List<CustomerEntity> remainingCustomers = this.customerRepository.findAll();

    // then
    assertThat(remainingCustomers).hasSize(1);
    assertThat(remainingCustomers.get(0).getId()).isNotEqualTo(removedCustomerId);
  }

}
