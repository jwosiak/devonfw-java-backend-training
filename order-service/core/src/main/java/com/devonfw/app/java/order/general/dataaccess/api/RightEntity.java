package com.devonfw.app.java.order.general.dataaccess.api;

import javax.persistence.Entity;

import com.devonfw.app.java.order.general.common.api.Right;

/**
 * @author Jakub Wosiak, Capgemini
 */
@Entity(name = "Right")
public class RightEntity extends ApplicationPersistenceEntity implements Right {

  private String name;

  private static final long serialVersionUID = 1L;

  @Override
  public String getName() {

    return this.name;
  }

  @Override
  public void setName(String name) {

    this.name = name;
  }

}
