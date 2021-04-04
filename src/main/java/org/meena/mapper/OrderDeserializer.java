package org.meena.mapper;

import org.meena.model.Order;

public interface OrderDeserializer {

  Order deserializer(String[] arr);

}
