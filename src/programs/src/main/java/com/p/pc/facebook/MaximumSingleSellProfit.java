package com.p.pc.facebook;

/**
 * <p>Given an array containing stock prices for n days. Find out the maximum single sell profit during these n days.
 * Stock must be purchased before it could be sold. It can only be bought once and sold once (on a different day)
 * during the given n days.
 * </p>
 * <p>Approach:</p>
 *  Use two pointers, one for buy index and other for sell index. Also keep track of max sell and min buy index.
 *  Buy Index will start with 0 and sell Index will start with 1.
 *  Iterate over the array and increase the sell index. Check profit for each sell index and if profit is higher than
 *  the max profit so far then update the minBuyIndex and maxSellIndex.
 *  If the profit is negative that means sell price is lower than the buy price, i.e. a lower buy price is found so move
 *  the buyIndex to current sell index.
 *
 *  <p>Time Complexity: O(n)</p>
 */
public class MaximumSingleSellProfit {
    public static void main(String[] args) {
        System.out.println(findBuySellStockPrices(new int[]{8, 5, 12, 9, 19, 1})); // Output: Tuple{buyPrice=5, sellPrice=19}
        System.out.println(findBuySellStockPrices(new int[]{21, 12, 11, 9, 6, 3})); // Output: Tuple{buyPrice=12, sellPrice=11}
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
            // if pnl is negative, that means sell price is lower than buy price hence move the buy index to sell index
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
