package org.meena.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TradeRecord {

  // <buy-order-id> <sell-price> <qty> <sell-order-id>
  private final String buyOrderId;
  private final Double sellPrice;
  private final String stock;
  private final Integer quantity;
  private final String sellOrderId;

}
