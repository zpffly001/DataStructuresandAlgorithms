package xyz.zhoupf.algorithm_classification.greedyalgorithm;

/**
 *
 */
public class _1217PlayingChips {

    //奇数移动到奇数、偶数移动到偶数是无消耗的，意思就是算奇数和偶数的个数而已。
    public int minCostToMoveChips(int[] chips) {
        int odd = 0, even = 0;
        for (int i = 0; i < chips.length; i++) {
            if (chips[i] % 2 == 0) {
                even++;
            } else if (chips[i] % 2 != 0) {
                odd++;
            }
        }
        return Math.min(even, odd);
    }

}
