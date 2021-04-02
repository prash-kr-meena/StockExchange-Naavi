package org.meena.fileio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReader implements InputReader {

  public void readFileLineByLine(String fileName) {
    List<String> lines;
    try {
      lines = Files.readAllLines(Paths.get(fileName));
      System.out.println(lines);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    String fileName = "src/main/java/org/meena/textfile/Input.txt";
    FileReader fr = new FileReader();
    fr.readFileLineByLine(fileName);
  }


}
