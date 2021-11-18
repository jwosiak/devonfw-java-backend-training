package com.devonfw.app.java.order.general.common.api;

public interface User extends ApplicationEntity {

  /**
   * @return nameId
   */
  public String getName();

  public String getPassword();

  /**
   * @param name setter for name attribute
   */
  public void setName(String name);

  /**
   * @param password setter for password attribute
   */
  public void setPassword(String password);

}
