package org.meena.strategy;

import org.meena.model.Order;
import org.meena.repository.OrderRepository;

public class ProcessingStrategy implements OrderProcessingStrategy {

  OrderRepository orderRepository;

  public ProcessingStrategy(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public void process(Order order) {
    int unfulfilledQuantity = orderRepository.matchAndFulfillOrders(order);
    if (unfulfilledQuantity > 0) {
      orderRepository.add(order);
    }
    // orderRepository.add(order);
  }

}
