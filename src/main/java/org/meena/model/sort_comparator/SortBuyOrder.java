package org.meena.model.sort_comparator;

import java.util.Comparator;
import org.meena.model.Order;

public class SortBuyOrder implements Comparator<Order> {

  @Override
  public int compare(Order a, Order b) {
    // The general sorting will be price-time sorting
    // But if the price is same, it will be sorted on time
    // for BUY TYPE : sorting on time is increasing

    // priority on price first - Here considering precision of 2 decimal
    int aPrice = (int) (a.getPrice() * 100);
    int bPrice = (int) (b.getPrice() * 100);
    int diff = bPrice - aPrice;

    if (diff != 0) {
      return diff;
    } else {
      // For Buy - Time should be sorted in Increasing Order
      return a.getTime().compareTo(b.getTime());
    }
  }

}
