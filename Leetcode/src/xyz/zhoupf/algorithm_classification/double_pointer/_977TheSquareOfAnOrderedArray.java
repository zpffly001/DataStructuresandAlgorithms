package xyz.zhoupf.algorithm_classification.double_pointer;

import java.util.Arrays;

/**
 * 977, 有序数组的平方
 */
public class _977TheSquareOfAnOrderedArray {

    public int[] sortedSquares(int[] A) {
        int N = A.length;
        int[] ans = new int[N];
        for (int i = 0; i < A.length; i++)
            ans[i] = A[i] * A[i];
        Arrays.sort(ans);
        return ans;
    }

}
