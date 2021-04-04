package org.meena.repository;

import org.meena.model.Order;

public interface OrderRepository {

  void add(Order order);

  int matchAndFulfillOrders(Order currOrder);

  boolean remove(Order order);

  boolean remove(Order order, Integer quantity);

  void showRepository();

}
