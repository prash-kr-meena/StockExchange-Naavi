package org.meena;

import org.meena.fileio.FileReader;
import org.meena.fileio.InputReader;
import org.meena.mapper.OrderMapper;
import org.meena.mapper.OrderMapperImpl;
import org.meena.strategy.OrderProcessingStrategy;
import org.meena.strategy.ProcessingStrategy;

public class App {

  private static final String inputFileName = "src/main/java/org/meena/textfile/Input.txt";
  private static final String outputFileName = "src/main/java/org/meena/textfile/Output.txt";

  public static void main(String[] args) {
    OrderMapper orderMapper = new OrderMapperImpl();
    OrderProcessingStrategy processingStrategy = new ProcessingStrategy();
    InputReader reader = new FileReader();
    reader.processLineByLine(inputFileName, orderMapper, processingStrategy);
  }
}
