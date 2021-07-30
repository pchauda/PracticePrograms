package com.p.pc.facebook;

public class MaximumSingleSellProfit {
    public static void main(String[] args) {
        int[] prices1 = new int[]{8, 5, 12, 9, 19, 1};
        int[] prices2 = new int[]{21, 12, 11, 9, 6, 3};
        System.out.println(findBuySellStockPrices(prices1)); // Output: Tuple{buyPrice=5, sellPrice=19}
        System.out.println(findBuySellStockPrices(prices2)); // Output: Tuple{buyPrice=12, sellPrice=11}
    }

    public static Tuple findBuySellStockPrices(int[] array) {
        if(array == null || array.length < 2) return null;
        int buyIndex = 0, minBuyIndex = 0;
        int sellIndex = 1, maxSellIndex = 1;
        int maxPnL = Integer.MIN_VALUE;
        do {
            int tmpPnl = array[sellIndex] - array[buyIndex];
            if(tmpPnl > maxPnL) {
                maxPnL = tmpPnl;
                minBuyIndex = buyIndex;
                maxSellIndex = sellIndex;
            }
            if(tmpPnl < 0)
                buyIndex = sellIndex;
            sellIndex++;
        } while(sellIndex < array.length);

        Tuple<Integer, Integer> result = new Tuple<Integer, Integer>(array[minBuyIndex], array[maxSellIndex]);
        return result;
    }

    static class Tuple<X, Y> {
        public X x;
        public Y y;

        public Tuple(X x, Y y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Tuple{" +
                    "buyPrice=" + x +
                    ", sellPrice=" + y +
                    '}';
        }
    }
}
