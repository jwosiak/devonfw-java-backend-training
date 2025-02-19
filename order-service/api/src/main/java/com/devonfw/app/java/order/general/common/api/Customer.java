package com.devonfw.app.java.order.general.common.api;

public interface Customer extends ApplicationEntity {

  /**
   * @return firstnameId
   */

  public String getFirstname();

  /**
   * @param firstname setter for firstname attribute
   */

  public void setFirstname(String firstname);

  /**
   * @return lastnameId
   */

  public String getLastname();

  /**
   * @param lastname setter for lastname attribute
   */

  public void setLastname(String lastname);

}
