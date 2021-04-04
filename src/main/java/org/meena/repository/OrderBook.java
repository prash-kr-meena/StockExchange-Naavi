package org.meena.repository;

import static org.meena.model.OrderType.BUY;

import java.util.ArrayList;
import java.util.List;
import org.meena.fileio.OutputWriter;
import org.meena.model.Order;
import org.meena.model.TradeRecord;
import org.meena.model.comparator.SortBuyOrder;
import org.meena.model.comparator.SortSellOrder;

public class OrderBook implements OrderRepository {

  OutputWriter outputWriter;
  List<Order> buyOrders;
  List<Order> sellOrders;

  public OrderBook(OutputWriter outputWriter) {
    this.outputWriter = outputWriter;
    this.buyOrders = new ArrayList<>();
    this.sellOrders = new ArrayList<>();
  }


  @Override
  public void add(Order order) {
    if (order.getType() == BUY) {
      buyOrders.add(order);
      buyOrders.sort(new SortBuyOrder());
    } else {
      sellOrders.add(order);
      sellOrders.sort(new SortSellOrder());
    }
  }


  public int matchAndFulfillOrders(Order currentOrder) {
    if (currentOrder.getType() == BUY) {
      return matchAndFulfillFromSellOrders(currentOrder);
    } else {
      return matchAndFulfillFromBuyOrders(currentOrder);
    }
  }

  private int matchAndFulfillFromSellOrders(Order buyOrder) {
    // Here for matching we will be going from lower-price to higher-price
    // our sellOrders are sorted in decreasing order of price & decreasing order of time
    // ie, we will be traversing in the reverse order to fulfill buy request

    for (int i = sellOrders.size() - 1; i >= 0; i--) {
      Order sellOrder = sellOrders.get(i);

      if (isDifferentStocks(buyOrder, sellOrder)) {
        continue;
      }

      boolean insufficientAmount = buyOrder.getPrice() < sellOrder.getPrice();
      if (insufficientAmount) {
        continue;
      }

      int fulfilledBuyOrders;

      if (buyOrder.getQuantity() > sellOrder.getQuantity()) {
        fulfilledBuyOrders = sellOrder.getQuantity();
        int remainingBuyOrders = buyOrder.getQuantity() - sellOrder.getQuantity();
        sellOrder.setQuantity(0);
        buyOrder.setQuantity(remainingBuyOrders);
      } else if (buyOrder.getQuantity() < sellOrder.getQuantity()) {
        fulfilledBuyOrders = buyOrder.getQuantity();
        int remainingSellOrders = sellOrder.getQuantity() - buyOrder.getQuantity();
        sellOrder.setQuantity(remainingSellOrders);
        buyOrder.setQuantity(0);
      } else {  // Both have same quantity
        fulfilledBuyOrders = sellOrder.getQuantity(); // or buyOrder.getQuantity();
        sellOrder.setQuantity(0);
        buyOrder.setQuantity(0);
      }

      TradeRecord tradeRecord = TradeRecord.builder()
          .buyOrderId(buyOrder.getId())
          .sellOrderId(sellOrder.getId())
          .sellPrice(sellOrder.getPrice())
          .stock(buyOrder.getStock())
          .quantity(fulfilledBuyOrders)
          .build();

      outputWriter.writeLineByLine(tradeRecord);
    }

    removeZeroQuantityOrders(sellOrders);
    return buyOrder.getQuantity();
  }

  private boolean isDifferentStocks(Order buyOrder, Order sellOrder) {
    return !buyOrder.getStock().equalsIgnoreCase(sellOrder.getStock());
  }

  private void removeZeroQuantityOrders(List<Order> orders) {
    orders.removeIf(order -> order.getQuantity() == 0);
    //    Iterator<Order> iterator = orders.iterator();
    //    while (iterator.hasNext()) {
    //      Order order = iterator.next();
    //      if (order.getQuantity() == 0) {
    //        iterator.remove();
    //      }
    //    }
  }


  private int matchAndFulfillFromBuyOrders(Order sellOder) {
    // Here for matching we will be going from higher-price to lower-price
    // basically, when we get a sell order we would wanna match the buyOder that is paying higher to buy compared to others
    // our sellOrders are sorted in decreasing order of price & increasing order of time
    // ie, we will be traversing in the normal order to fulfill buy request

    for (int i = 0; i < buyOrders.size(); i++) {
      Order buyOrder = buyOrders.get(i);

      if (isDifferentStocks(sellOder, buyOrder)) {
        continue;
      }

      boolean insufficientAmount = buyOrder.getPrice() < sellOder.getPrice();
      if (insufficientAmount) {
        continue;
      }

      int fulfilledSellOrders;

      if (sellOder.getQuantity() > buyOrder.getQuantity()) {
        fulfilledSellOrders = buyOrder.getQuantity();
        int remainingSellOrders = sellOder.getQuantity() - buyOrder.getQuantity();
        buyOrder.setQuantity(0);
        sellOder.setQuantity(remainingSellOrders);
      } else if (sellOder.getQuantity() < buyOrder.getQuantity()) {
        fulfilledSellOrders = sellOder.getQuantity();
        int remainingBuyOrders = buyOrder.getQuantity() - sellOder.getQuantity();
        buyOrder.setQuantity(remainingBuyOrders);
        sellOder.setQuantity(0);
      } else {  // Both have same quantity
        fulfilledSellOrders = buyOrder.getQuantity(); // or buyOrder.getQuantity();
        buyOrder.setQuantity(0);
        sellOder.setQuantity(0);
      }

      TradeRecord tradeRecord = TradeRecord.builder()
          .buyOrderId(buyOrder.getId())
          .sellOrderId(sellOder.getId())
          .sellPrice(sellOder.getPrice())
          .stock(sellOder.getStock())
          .quantity(fulfilledSellOrders)
          .build();

      outputWriter.writeLineByLine(tradeRecord);
    }

    removeZeroQuantityOrders(buyOrders);
    return sellOder.getQuantity();
  }

  @Override
  public boolean remove(Order order) {
    boolean removed;
    if (order.getType() == BUY) {
      removed = buyOrders.remove(order);
    } else {
      removed = sellOrders.remove(order);
    }

    return removed;
  }

  @Override
  public boolean remove(Order order, Integer quantity) {
    System.out.println("order removed with quantity");
    return false;
  }


  @Override
  public void showRepository() {
    System.out.println("SELL ORDERS");
    for (Order order : sellOrders) {
      System.out.println(order);
    }

    System.out.println("BUY ORDERS");
    for (Order order : buyOrders) {
      System.out.println(order);
    }

  }
}
