package org.meena.strategy;

import java.util.function.Consumer;
import org.meena.model.Order;

public class ProcessingStrategy implements OrderProcessingStrategy {


  @Override
  public Consumer<Order> process(Order arr) {
    System.out.println("strategy is working");
    return null;
  }
}
