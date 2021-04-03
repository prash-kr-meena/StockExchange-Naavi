package org.meena.fileio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import org.meena.mapper.OrderMapper;
import org.meena.strategy.OrderProcessingStrategy;

public class FileReader implements InputReader {

  public void processLineByLine(String fileName, OrderMapper mapper,
      OrderProcessingStrategy orderProcessingStrategy) {

    try {
      File file = new File(fileName);
      Path path = file.toPath();
      Stream<String> lines = Files.lines(path);

      lines
          .map(String::trim)
          .map(s -> s.split("\\s+"))
          .filter(arr -> arr.length > 0)
          .map(mapper::map)
          .forEach(orderProcessingStrategy::process);

      lines.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
