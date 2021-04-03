package org.meena.fileio;

import org.meena.mapper.OrderMapper;
import org.meena.strategy.OrderProcessingStrategy;

public interface InputReader {

  void processLineByLine(String fileName, OrderMapper mapper,
      OrderProcessingStrategy orderProcessingStrategy);

}
