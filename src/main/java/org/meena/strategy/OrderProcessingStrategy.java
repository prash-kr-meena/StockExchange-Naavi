package org.meena.strategy;

import org.meena.model.Order;

public interface OrderProcessingStrategy {

  void process(Order arr);

}
