package org.meena.mapper;

import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.meena.model.Order;
import org.meena.model.OrderType;

@Slf4j
public class OrderDeserializerImpl implements OrderDeserializer {

  @Override
  public Order deserializer(String[] input) {
    Order order = null;
    try {
      order = Order.builder()
          .id(input[0])
          .time(input[1])
          .stock(input[2])
          .type("buy".equalsIgnoreCase(input[3]) ? OrderType.BUY : OrderType.SELL)
          .price(Double.parseDouble(input[4]))
          .quantity(Integer.parseInt(input[5]))
          .build();
    } catch (Exception e) {
      log.error("Issue parsing the input : {}", Arrays.toString(input));
      e.printStackTrace();
    }
    return order;
  }

}
