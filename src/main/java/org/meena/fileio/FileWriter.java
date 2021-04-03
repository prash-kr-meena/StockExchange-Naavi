package org.meena.fileio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileWriter implements OutputWriter {

  public static void main(String[] args) {
    String fileName = "src/main/java/org/meena/textfile/Output.txt";
    FileWriter fw = new FileWriter();
    fw.writeLineByLine(fileName, "hello");
  }

  @Override
  public void writeLineByLine(String fileName, String content) {
    try {
      Path path = Paths.get(fileName);
      byte[] bytes = content.getBytes();
      Files.write(path, bytes, StandardOpenOption.CREATE);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
