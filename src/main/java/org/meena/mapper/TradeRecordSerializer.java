package org.meena.mapper;

import org.meena.model.TradeRecord;

public interface TradeRecordSerializer {

  String serialize(TradeRecord tradeRecord);

  String header();

}
