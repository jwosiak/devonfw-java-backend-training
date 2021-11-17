package com.devonfw.app.java.order.common.api.builders;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

import com.devonfw.app.java.order.general.logic.api.to.CustomerEto;

/**
 * Test data builder for CustomerEto generated with cobigen.
 */
public class CustomerEtoBuilder {

  private List<Consumer<CustomerEto>> parameterToBeApplied;

  /**
   * The constructor.
   */
  public CustomerEtoBuilder() {

    this.parameterToBeApplied = new LinkedList<>();
    fillMandatoryFields();
    fillMandatoryFields_custom();
  }

  /**
   * @param firstname the firstname to add.
   * @return the builder for fluent population of fields.
   */
  public CustomerEtoBuilder firstname(final String firstname) {

    this.parameterToBeApplied.add(target -> target.setFirstname(firstname));

    return this;
  }

  /**
   * @param lastname the lastname to add.
   * @return the builder for fluent population of fields.
   */
  public CustomerEtoBuilder lastname(final String lastname) {

    this.parameterToBeApplied.add(target -> target.setLastname(lastname));

    return this;
  }

  /**
   * @return the populated CustomerEto.
   */
  public CustomerEto createNew() {

    CustomerEto customereto = new CustomerEto();
    for (Consumer<CustomerEto> parameter : this.parameterToBeApplied) {
      parameter.accept(customereto);
    }
    return customereto;
  }

  /**
   * Might be enriched to users needs (will not be overwritten)
   */
  private void fillMandatoryFields_custom() {

  }

  /**
   * Fills all mandatory fields by default. (will be overwritten on re-generation)
   */
  private void fillMandatoryFields() {

  }

}
