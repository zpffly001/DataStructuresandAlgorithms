package xyz.zhoupf.algorithm_classification.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 56. 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 * 示例 1:    输入: intervals = [[1,3],[2,6],[8,10],[15,18]]    输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:    输入: intervals = [[1,4],[4,5]]   输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 * 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。
 */
public class _56ConsolidationInterval {

    public static int[][] merge(int[][] intervals) {
        int len = intervals.length;
        if (len < 2) return intervals;

        //按照各个子数组的起点(即intervals[i][0]，即最左边)进行排序
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

        // 也可以使用 Stack，因为我们只关心结果集的最后一个区间
        List<int[]> res = new ArrayList<>();
        res.add(intervals[0]);

        for (int i = 1; i < len; i++) {
            //当前遍历到的子数组
            int[] curInterval = intervals[i];

            //结果集res中最后一个元素
            int[] peek = res.get(res.size() - 1);

            // 每次新遍历到的列表与当前结果集中的最后一个区间的末尾端点进行比较
            if (curInterval[0] > peek[1]){
                res.add(curInterval);
            }else {
                // 注意，这里应该取大的，即合并了两个重复的区间，数组也是引用，改变此引用数组，既能改变res结果集中对应的子数组的范围
                peek[1] = Math.max(curInterval[1], peek[1]);
            }
        }
        return res.toArray(new int[res.size()][]);
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new ArrayList<>();
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(4);
        arrayList.clear();
        lists.add(arrayList);
        arrayList.add(4);
        arrayList.add(5);
        int[][] arr = {{1, 4}, {4, 5}};
        System.out.println(merge(arr)[0][0] + ", " + merge(arr)[0][1]);
    }

}
