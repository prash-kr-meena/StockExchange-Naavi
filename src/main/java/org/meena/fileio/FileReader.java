package org.meena.fileio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.meena.mapper.OrderDeserializer;
import org.meena.strategy.OrderProcessingStrategy;

public class FileReader implements InputReader {

  private final OrderDeserializer deserializer;
  private final OrderProcessingStrategy orderProcessingStrategy;

  public FileReader(OrderDeserializer deSerializer,
      OrderProcessingStrategy orderProcessingStrategy) {

    this.deserializer = deSerializer;
    this.orderProcessingStrategy = orderProcessingStrategy;
  }

  public void processLineByLine(String fileName) {
    try {
      File file = new File(fileName);
      Path path = file.toPath();
      Stream<String> lines = Files.lines(path);

      lines
          .map(String::trim)
          .map(s -> s.split("\\s+"))
          .filter(arr -> arr.length > 0)
          .map(deserializer::deserializer)
          .forEach(orderProcessingStrategy::process);

      lines.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
