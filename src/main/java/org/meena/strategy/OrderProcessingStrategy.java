package org.meena.strategy;

import java.util.function.Consumer;
import org.meena.model.Order;

public interface OrderProcessingStrategy {

  public Consumer<Order> process(Order arr);

}
