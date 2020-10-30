package xyz.zhoupf.primary_algorithm.chuji01_shuzu;

/**
 * 贪心算法
 * 买股票的最佳时机2
 */

public class chuji_shuzu02 {

    public static int maxProfit(int[] prices) {
        int sum = 0;
        if(prices == null || prices.length ==0){
            return 0;
        }else{
            for(int i = 1; i <= prices.length-1; i++){
                int j = prices[i] - prices[i-1];
                if(j > 0){
                    sum += j;
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }

}
