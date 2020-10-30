package xyz.zhoupf.primary_algorithm.chuji06_dynamic_programming;

/**
 * 买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票。

 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 */
public class chuji02_maimaigupiao {

    public static int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }

    //
    public static int maxProfit01(int prices[]) {
        int buy = 0;
        int sale = 0;
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            buy = prices[i];
            for (int j = i + 1; j < prices.length; j++) {
                if (buy < prices[j] && max < (prices[j] - buy)){
                    max = prices[j] - buy;
                }
            }
        }
        return max;
    }

    public static int maxProfit02(int prices[]) {
        int max = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] - prices[i] > max)
                    max = prices[j] - prices[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = {7, 1, 5 ,3, 6, 4};
        System.out.println(maxProfit(arr));
    }

}
