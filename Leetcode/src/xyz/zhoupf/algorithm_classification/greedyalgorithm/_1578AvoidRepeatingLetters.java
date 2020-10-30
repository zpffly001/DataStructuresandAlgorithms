package xyz.zhoupf.algorithm_classification.greedyalgorithm;

/**
 * 给你一个字符串 s 和一个整数数组 cost ，其中 cost[i] 是从 s 中删除字符 i 的代价。
 * 返回使字符串任意相邻两个字母不相同的最小删除成本。
 * 请注意，删除一个字符后，删除其他字符的成本不会改变。

 * 示例 1：
 * 输入：s = "abaac", cost = [1,2,3,4,5]   输出：3
 * 解释：删除字母 "a" 的成本为 3，然后得到 "abac"（字符串中相邻两个字母不相同）。
 * 示例 2：
 * 输入：s = "abc", cost = [1,2,3] 输出：0
 * 解释：无需删除任何字母，因为字符串中不存在相邻两个字母相同的情况。
 */
public class _1578AvoidRepeatingLetters {

    public static int minCost(String s, int[] cost) {
        char[] chars = s.toCharArray();
        int slowPoint = 0, sum = 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[slowPoint]){
                //sum += cost[i]>cost[slowPoint]?cost[slowPoint]:cost[i];
                if (cost[i]>=cost[slowPoint]) sum += cost[slowPoint];
                else {
                    sum += cost[i];
                    cost[i] = cost[slowPoint];
                }
            }
            slowPoint++;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 1};
        String s = "aabaa";
        System.out.println(minCost(s, arr));
    }


}
