package org.meena;

import org.meena.fileio.FileReader;
import org.meena.fileio.FileWriter;
import org.meena.fileio.InputReader;
import org.meena.fileio.OutputWriter;
import org.meena.mapper.OrderDeserializer;
import org.meena.mapper.OrderDeserializerImpl;
import org.meena.mapper.TradeRecordSerializer;
import org.meena.mapper.TradeRecordSerializerImpl;
import org.meena.repository.OrderBook;
import org.meena.repository.OrderRepository;
import org.meena.strategy.OrderProcessingStrategy;
import org.meena.strategy.ProcessingStrategy;

public class App {

  private static final String inputFileName = "src/main/java/org/meena/textfile/Input.txt";
  private static final String outputFileName = "src/main/java/org/meena/textfile/Output.txt";

  public static void main(String[] args) {
    TradeRecordSerializer tradeRecordSerializer = new TradeRecordSerializerImpl();
    OutputWriter outputWriter = new FileWriter(outputFileName, tradeRecordSerializer);
    OrderRepository orderRepo = new OrderBook(outputWriter);
    OrderProcessingStrategy processingStrategy = new ProcessingStrategy(orderRepo); // Injection

    OrderDeserializer orderDeSerializer = new OrderDeserializerImpl();

    InputReader reader = new FileReader(orderDeSerializer, processingStrategy);
    reader.processLineByLine(inputFileName);

    orderRepo.showRepository();
  }
}
