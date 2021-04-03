package org.meena.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {

  private final String id;
  private final String time;
  private final String stock;
  private final OrderType type;
  private final Double price;
  private final Integer quantity;
}
