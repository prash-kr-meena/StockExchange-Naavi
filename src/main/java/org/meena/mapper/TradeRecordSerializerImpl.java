package org.meena.mapper;

import java.util.Formatter;
import org.meena.model.TradeRecord;

public class TradeRecordSerializerImpl implements TradeRecordSerializer {


  public static final String SPACE = " ";
  public static final String NEW_LINE = "\n";
  public static final String TAB = "\t";


  @Override
  public String serialize(TradeRecord tradeRecord) {
    // <buy-order-id> <sell-price> <qty> <sell-order-id>
    Formatter formatter = new Formatter();

    formatter
        .format("%-3s ", tradeRecord.getBuyOrderId())
        .format("%-5.2f ", tradeRecord.getSellPrice())
        .format("%-3s ", tradeRecord.getStock())
        .format("%-4d ", tradeRecord.getQuantity())
        .format("%-3s ", tradeRecord.getSellOrderId());

    return formatter.toString() + NEW_LINE;
  }


  @Override
  public String header() {
    return "<buy-id> <sell-price> <qty> <sell-id>\n";
    //    return "";
  }

}
