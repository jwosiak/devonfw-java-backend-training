package com.devonfw.app.java.order.general.common.api;

public interface Item extends ApplicationEntity {

  /**
   * @return descriptionId
   */

  public String getDescription();

  /**
   * @param description setter for description attribute
   */

  public void setDescription(String description);

  /**
   * @return nameId
   */

  public String getName();

  /**
   * @param name setter for name attribute
   */

  public void setName(String name);

  /**
   * @return PriceId
   */

  public double getPrice();

  /**
   * @param Price setter for Price attribute
   */

  public void setPrice(double Price);

}
