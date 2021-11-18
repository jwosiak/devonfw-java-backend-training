package com.devonfw.app.java.order.general.dataaccess.api;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.devonfw.app.java.order.general.common.api.Role;

/**
 * @author Jakub Wosiak, Capgemini
 */
@Entity(name = "Role")
public class RoleEntity extends ApplicationPersistenceEntity implements Role {

  private String name;

  private Set<RightEntity> rights;

  private static final long serialVersionUID = 1L;

  @Override
  public String getName() {

    return this.name;
  }

  @Override
  public void setName(String name) {

    this.name = name;
  }

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "RoleRight", joinColumns = @JoinColumn(name = "roleId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "rightId", referencedColumnName = "id"))
  public Set<RightEntity> getRights() {

    return this.rights;
  }

  public void setRights(Set<RightEntity> rights) {

    this.rights = rights;
  }

}
