package xyz.zhoupf.algorithm_classification.double_pointer;

import java.util.ArrayList;
import java.util.List;

/**
 *  986-区间列表的交集
 */
public class _986IntersectionOfIntervalList {

    /**
     * 在数组 B 的区间中， A[0] 只可能与数组 B 中的至多一个区间相交。
     * 如果 A[0] 拥有最小的末端点，那么它只可能与 B[0] 相交。
     * 然后我们就可以删除区间 A[0]，因为它不能与其他任何区间再相交了。
     */
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> ans = new ArrayList<>();
        int i = 0, j = 0;

        while (i < A.length && j < B.length){
            int lo = Math.max(A[i][0], B[j][0]);
            int hi = Math.min(A[i][1], B[j][1]);
            if (lo <= hi)
                ans.add(new int[]{lo, hi});
            if (A[i][1] < B[j][1])
                i++;
            else
                j++;
        }
        return ans.toArray(new int[ans.size()][]);
    }

}
