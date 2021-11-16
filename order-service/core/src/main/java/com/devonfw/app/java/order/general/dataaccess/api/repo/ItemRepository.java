package com.devonfw.app.java.order.general.dataaccess.api.repo;

import static com.querydsl.core.alias.Alias.$;

import java.util.Arrays;

import org.springframework.data.domain.Page;

import com.devonfw.app.java.order.general.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.module.jpa.dataaccess.api.QueryUtil;
import com.devonfw.module.jpa.dataaccess.api.data.DefaultRepository;
import com.querydsl.jpa.impl.JPAQuery;

/**
 * @author Jakub Wosiak, Capgemini
 *
 */
public interface ItemRepository extends DefaultRepository<ItemEntity> {

  default Page<ItemEntity> findByCriteria(ItemSearchCriteriaTo criteria) {

    ItemEntity alias = newDslAlias();
    JPAQuery<ItemEntity> query = newDslQuery(alias);
    QueryUtil queryUtil = QueryUtil.get();

    String name = criteria.getName();
    if (name != null && !name.isEmpty()) {
      queryUtil.whereString(query, $(alias.getName()), name, criteria.getNameOption());
    }

    String description = criteria.getDescription();
    if (description != null && !description.isEmpty()) {
      queryUtil.whereString(query, $(alias.getDescription()), description, criteria.getDescriptionOption());
    }

    Double price = criteria.getPrice();
    if (price != null) {
      queryUtil.whereIn(query, $(alias.getPrice()), Arrays.asList(price));
    }

    return queryUtil.findPaginated(criteria.getPageable(), query, true);
  }

  ItemEntity findByName(String name);

}
