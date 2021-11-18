package com.devonfw.app.java.order.general.common.api.security;

import java.util.Arrays;

import javax.inject.Named;

import org.springframework.context.annotation.Primary;

import com.devonfw.module.security.common.api.accesscontrol.AccessControlGroup;
import com.devonfw.module.security.common.base.accesscontrol.AccessControlConfig;

/**
 * Example of {@link AccessControlConfig} that used for testing.
 */
@Named
@Primary
public class ApplicationAccessControlConfig extends AccessControlConfig {

  public static final String APP_ID = "order-service";

  private static final String PREFIX = APP_ID + ".";

  public static final String PERMISSION_FIND_BINARY_OBJECT = PREFIX + "FindBinaryObject";

  public static final String PERMISSION_SAVE_BINARY_OBJECT = PREFIX + "SaveBinaryObject";

  public static final String PERMISSION_DELETE_BINARY_OBJECT = PREFIX + "DeleteBinaryObject";

  public static final String GROUP_READ_MASTER_DATA = PREFIX + "ReadMasterData";

  public static final String GROUP_ADMIN = PREFIX + "Admin";

  public static final String PERMISSION_ADD_ITEM = PREFIX + "AddItem";

  public static final String PERMISSION_ADD_ORDER = PREFIX + "AddOrder";

  public static final String GROUP_WAITER = PREFIX + "Waiter";

  public static final String GROUP_COOK = PREFIX + "Chef";

  /**
   * The constructor.
   */
  public ApplicationAccessControlConfig() {

    super();
    AccessControlGroup readMasterData = group(GROUP_READ_MASTER_DATA, PERMISSION_FIND_BINARY_OBJECT);
    AccessControlGroup waiterGroup = group(GROUP_WAITER, PERMISSION_ADD_ORDER);
    AccessControlGroup cookGroup = group(GROUP_COOK, PERMISSION_ADD_ITEM);
    group(GROUP_ADMIN, Arrays.asList(readMasterData, waiterGroup, cookGroup), PERMISSION_SAVE_BINARY_OBJECT,
        PERMISSION_DELETE_BINARY_OBJECT);
  }

}