package com.devonfw.app.java.order.orderservice.logic.impl.usecase;

import javax.inject.Named;
import javax.transaction.Transactional;

import org.springframework.validation.annotation.Validated;

import com.devonfw.app.java.order.general.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.general.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageItem;

/**
 * @author Jakub Wosiak, Capgemini
 *
 */
@Named
@Validated
@Transactional
public class UcManageItemImpl extends AbstractItemUc implements UcManageItem {

  @Override
  public boolean deleteItem(long itemId) {

    if (getItemRepository().existsById(itemId)) {
      getItemRepository().deleteById(itemId);
      return true;
    }
    return false;
  }

  @Override
  public ItemEto saveItem(ItemEto item) {

    ItemEntity itemEntity = getBeanMapper().map(item, ItemEntity.class);
    itemEntity = getItemRepository().save(itemEntity);
    return getBeanMapper().map(itemEntity, ItemEto.class);
  }

  @Override
  public void increasePrice(String itemName, double priceGrowth) {

    ItemEntity item = getItemRepository().findByName(itemName);
    item.setPrice(item.getPrice() + priceGrowth);
    getItemRepository().save(item);
  }

}
