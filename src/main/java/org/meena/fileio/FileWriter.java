package org.meena.fileio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.meena.mapper.TradeRecordSerializer;
import org.meena.model.TradeRecord;

public class FileWriter implements OutputWriter {

  String fileName;
  TradeRecordSerializer tradeRecordSerializer;

  public FileWriter(String fileName, TradeRecordSerializer tradeRecordSerializer) {
    this.fileName = fileName;
    this.tradeRecordSerializer = tradeRecordSerializer;
    writeContent(tradeRecordSerializer.header());
  }

  @Override
  public void writeLineByLine(TradeRecord tradeRecord) {
    String content = tradeRecordSerializer.serialize(tradeRecord);
    writeContent(content);
  }

  private void writeContent(String content) {
    try {
      Path path = Paths.get(fileName);
      byte[] bytes = content.getBytes();
      Files.write(path, bytes, StandardOpenOption.APPEND);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
