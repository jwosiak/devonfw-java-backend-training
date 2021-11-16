package com.devonfw.app.java.order.general.dataaccess.api.repo;

import static org.junit.Assume.assumeThat;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.hamcrest.core.IsEqual;
import org.hamcrest.core.IsNot;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.devonfw.app.java.order.general.dataaccess.api.ItemEntity;
import com.devonfw.app.java.order.orderservice.logic.api.to.ItemSearchCriteriaTo;
import com.devonfw.module.basic.common.api.query.StringSearchConfigTo;
import com.devonfw.module.basic.common.api.query.StringSearchOperator;
import com.devonfw.module.test.common.base.ComponentTest;

/**
 * @author Jakub Wosiak, Capgemini
 *
 */
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class ItemRepositoryTest extends ComponentTest {

  @Inject
  ItemRepository itemRepository;

  @Override
  protected void doTearDown() {

    super.doTearDown();
    this.itemRepository.deleteAll();
  }

  @Before
  public void prepareData() {

    ItemEntity bolognese = new ItemEntity();
    bolognese.setDescription("Italy");
    bolognese.setName("spaghetti bolognese");
    bolognese.setPrice(250);

    ItemEntity carbonara = new ItemEntity();
    carbonara.setDescription("Italy");
    carbonara.setName("spaghetti carbonara");
    carbonara.setPrice(270);

    ItemEntity schabowy = new ItemEntity();
    schabowy.setDescription("Poland");
    schabowy.setName("kotlet schabowy");
    schabowy.setPrice(220);

    this.itemRepository.saveAll(Arrays.asList(bolognese, carbonara, schabowy));
  }

  @Test
  public void shouldFindAllItems() {

    // when
    List<ItemEntity> foundItems = this.itemRepository.findAll();

    // then
    assertThat(foundItems).hasSize(3);
  }

  @Test
  public void findByName() {

    // given
    Pageable pageable = PageRequest.of(0, 20);
    ItemSearchCriteriaTo criteria = new ItemSearchCriteriaTo();
    StringSearchConfigTo nameOption = new StringSearchConfigTo();
    criteria.setPageable(pageable);
    criteria.setName("spaghetti");
    nameOption.setMatchSubstring(true);
    criteria.setNameOption(nameOption);

    // when
    Page<ItemEntity> foundItems = this.itemRepository.findByCriteria(criteria);

    // then
    assertThat(foundItems).hasSize(2);
    assertThat(foundItems).anySatisfy(item -> assertThat(item.getName()).isEqualTo("spaghetti bolognese"));
    assertThat(foundItems).anySatisfy(item -> assertThat(item.getName()).isEqualTo("spaghetti carbonara"));
  }

  @Test
  public void findByDescription() {

    // given
    Pageable pageable = PageRequest.of(0, 20);
    ItemSearchCriteriaTo criteria = new ItemSearchCriteriaTo();
    StringSearchConfigTo descriptionOption = new StringSearchConfigTo();
    criteria.setPageable(pageable);
    criteria.setDescription("Poland");
    descriptionOption.setMatchSubstring(true);
    criteria.setDescriptionOption(descriptionOption);

    // when
    Page<ItemEntity> foundItems = this.itemRepository.findByCriteria(criteria);

    // then
    assertThat(foundItems).hasSize(1);
    assertThat(foundItems).anySatisfy(item -> assertThat(item.getName()).isEqualTo("kotlet schabowy"));
    assertThat(foundItems).anySatisfy(item -> assertThat(item.getDescription()).isEqualTo("Poland"));
  }

  @Test
  public void findByPrice() {

    // given
    Pageable pageable = PageRequest.of(0, 20);
    ItemSearchCriteriaTo criteria = new ItemSearchCriteriaTo();
    criteria.setPageable(pageable);
    criteria.setPrice(250.);

    // when
    Page<ItemEntity> foundItems = this.itemRepository.findByCriteria(criteria);

    // then
    assertThat(foundItems).hasSize(1);
    assertThat(foundItems).anySatisfy(item -> assertThat(item.getName()).isEqualTo("spaghetti bolognese"));
    assertThat(foundItems).anySatisfy(item -> assertThat(item.getPrice()).isEqualTo(250));
  }

  @Test
  public void findAllInAscendingOrder() {

    // given
    Sort sort = Sort.by(Direction.ASC, "name");
    Pageable pageable = PageRequest.of(0, 20, sort);
    ItemSearchCriteriaTo criteria = new ItemSearchCriteriaTo();
    criteria.setPageable(pageable);

    // when
    List<ItemEntity> foundItems = this.itemRepository.findByCriteria(criteria).getContent();

    // then
    assertThat(foundItems).hasSize(3);
    assertThat(foundItems.get(0).getName()).isEqualTo("kotlet schabowy");
    assertThat(foundItems.get(1).getName()).isEqualTo("spaghetti bolognese");
    assertThat(foundItems.get(2).getName()).isEqualTo("spaghetti carbonara");
  }

  @Test
  public void findAllInDescendingOrder() {

    // given
    Sort sort = Sort.by(Direction.DESC, "price");
    Pageable pageable = PageRequest.of(0, 20, sort);
    ItemSearchCriteriaTo criteria = new ItemSearchCriteriaTo();
    criteria.setPageable(pageable);

    // when
    List<ItemEntity> foundItems = this.itemRepository.findByCriteria(criteria).getContent();

    // then
    assertThat(foundItems).hasSize(3);
    assertThat(foundItems.get(0).getPrice()).isEqualTo(270);
    assertThat(foundItems.get(1).getPrice()).isEqualTo(250);
    assertThat(foundItems.get(2).getPrice()).isEqualTo(220);
  }

  @Test
  public void findLikeNameInAscendingOrder() {

    // given
    Sort sort = Sort.by(Direction.ASC, "name");
    Pageable pageable = PageRequest.of(0, 20, sort);
    ItemSearchCriteriaTo criteria = new ItemSearchCriteriaTo();
    StringSearchConfigTo nameOption = new StringSearchConfigTo();
    criteria.setPageable(pageable);
    criteria.setName("%TI%");
    nameOption.setIgnoreCase(true);
    nameOption.setOperator(StringSearchOperator.LIKE);
    criteria.setNameOption(nameOption);

    // when
    List<ItemEntity> foundItems = this.itemRepository.findByCriteria(criteria).getContent();

    // then
    assertThat(foundItems).hasSize(2);
    assertThat(foundItems.get(0).getName()).isEqualTo("spaghetti bolognese");
    assertThat(foundItems.get(1).getName()).isEqualTo("spaghetti carbonara");
  }

  @Test
  public void changePriceForItemWithGivenName() {

    // given
    ItemEntity item = this.itemRepository.findByName("kotlet schabowy");
    assumeThat(190., IsNot.not(IsEqual.equalTo(item.getPrice())));

    // when
    item.setPrice(190.);
    this.itemRepository.save(item);

    // then
    assertThat(this.itemRepository.findByName("kotlet schabowy").getPrice()).isEqualTo(190.);
  }

}
