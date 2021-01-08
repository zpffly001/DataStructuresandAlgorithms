package xyz.zhoupf.algorithm_classification.string;

/**
 * 输入：[5,5,5,10,20]
 * 输出：true
 * 解释：
 * 前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
 * 第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
 * 第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
 * 由于所有客户都得到了正确的找零，所以我们输出 true。
 */
public class _860LemonadeChange {

    public static boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five == 0) {
                    return false;
                }
                five--;
                ten++;
            }else {
                if (five > 0 && ten >0){
                    five--;
                    ten--;
                } else if (five >= 3) {
                    five -= 3;
                }else {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }

}
