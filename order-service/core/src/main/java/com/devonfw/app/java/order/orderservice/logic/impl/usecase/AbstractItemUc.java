package com.devonfw.app.java.order.orderservice.logic.impl.usecase;

import javax.inject.Inject;

import com.devonfw.app.java.order.general.dataaccess.api.repo.ItemRepository;
import com.devonfw.app.java.order.general.logic.base.AbstractUc;

/**
 * @author Jakub Wosiak, Capgemini
 *
 */
public abstract class AbstractItemUc extends AbstractUc {

  @Inject
  private ItemRepository itemRepository;

  public ItemRepository getItemRepository() {

    return this.itemRepository;
  }

}
