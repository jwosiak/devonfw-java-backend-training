package com.devonfw.app.java.order.general.dataaccess.api;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.devonfw.app.java.order.general.common.api.User;

/**
 * @author Jakub Wosiak, Capgemini
 */
@Entity(name = "User")
public class UserEntity extends ApplicationPersistenceEntity implements User {

  private String name;

  private String password;

  private Set<RoleEntity> roles;

  private static final long serialVersionUID = 1L;

  @Override
  public String getName() {

    return this.name;
  }

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "UserRole", joinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"))
  public Set<RoleEntity> getRoles() {

    return this.roles;
  }

  @Override
  public String getPassword() {

    return this.password;
  }

  @Override
  public void setPassword(String password) {

    this.password = password;
  }

  @Override
  public void setName(String name) {

    this.name = name;
  }

  public void setRoles(Set<RoleEntity> roles) {

    this.roles = roles;
  }

}
