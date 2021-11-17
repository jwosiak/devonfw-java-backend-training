package com.devonfw.app.java.order.orderservice.logic.api;

import com.devonfw.app.java.order.general.logic.api.usecase.UcFindCustomer;
import com.devonfw.app.java.order.general.logic.api.usecase.UcFindOrder;
import com.devonfw.app.java.order.general.logic.api.usecase.UcManageCustomer;
import com.devonfw.app.java.order.general.logic.api.usecase.UcManageOrder;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcFindItem;
import com.devonfw.app.java.order.orderservice.logic.api.usecase.UcManageItem;

/**
 * @author Jakub Wosiak, Capgemini
 *
 */
public interface Orderservice
    extends UcManageItem, UcFindItem, UcFindCustomer, UcManageCustomer, UcFindOrder, UcManageOrder {

}
