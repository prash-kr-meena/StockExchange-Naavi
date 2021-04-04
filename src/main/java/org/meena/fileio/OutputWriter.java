package org.meena.fileio;

import org.meena.model.TradeRecord;

public interface OutputWriter {

  void writeLineByLine(TradeRecord tradeRecord);
}
