package com.devonfw.app.java.order.orderservice.logic.impl.usecase;

import java.util.Optional;

import javax.inject.Named;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.validation.annotation.Validated;

import com.devonfw.app.java.order.general.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.general.logic.api.to.ItemEto;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindItem;
import com.devonfw.module.basic.common.api.query.StringSearchConfigTo;
import com.devonfw.module.basic.common.api.query.StringSearchOperator;

/**
 * @author Jakub Wosiak, Capgemini
 *
 */
@Named
@Validated
@Transactional
public class UcFindItemImpl extends AbstractItemUc implements UcFindItem {

  @Override
  public ItemEto findItem(long id) {

    Optional<ItemEntity> item = getItemRepository().findById(id);
    if (!item.isPresent()) {
      return null;
    }
    return entityToEto(item.get());
  }

  @Override
  public Page<ItemEto> findItems(ItemSearchCriteriaTo criteria) {

    return getItemRepository().findByCriteria(criteria).map(this::entityToEto);
  }

  private ItemEto entityToEto(ItemEntity item) {

    return getBeanMapper().map(item, ItemEto.class);
  }

  @Override
  public Page<ItemEto> findItemsOrderedByName(String likeName) {

    Sort sort = Sort.by(Direction.ASC, "name");
    Pageable pageable = PageRequest.of(0, 20, sort);
    ItemSearchCriteriaTo criteria = new ItemSearchCriteriaTo();
    StringSearchConfigTo nameOption = new StringSearchConfigTo();
    criteria.setPageable(pageable);
    criteria.setName("%" + likeName + "%");
    nameOption.setIgnoreCase(true);
    nameOption.setOperator(StringSearchOperator.LIKE);
    criteria.setNameOption(nameOption);
    return mapPaginatedEntityList(getItemRepository().findByCriteria(criteria), ItemEto.class);
  }

}
