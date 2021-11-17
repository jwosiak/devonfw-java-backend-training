package com.devonfw.app.java.order.orderservice.service.api.rest;

import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.devonfw.app.java.order.general.logic.api.to.ItemEto;
import com.devonfw.app.java.order.general.logic.api.to.OrderCto;
import com.devonfw.module.rest.common.api.RestService;

/**
 * @author Jakub Wosiak, Capgemini
 *
 */
@Path("/orderservice/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface OrderserviceRestService extends RestService {

  @GET
  @Path("/items/{name}")
  Set<ItemEto> findItemByName(@PathParam("name") String name);

  @PUT
  @Path("/order/save/")
  OrderCto saveOrder(OrderCto order);

}
