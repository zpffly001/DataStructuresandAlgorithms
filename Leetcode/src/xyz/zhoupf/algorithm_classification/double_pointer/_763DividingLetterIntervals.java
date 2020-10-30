package xyz.zhoupf.algorithm_classification.double_pointer;

import java.util.ArrayList;
import java.util.List;

public class _763DividingLetterIntervals {

    public static List<Integer> partitionLabels(String S) {
        int[] last = new int[26];
        //记录字符串S中，每个字母在字符串中最后一次出现的索引
        for (int i = 0; i < S.length(); i++) {
            last[S.charAt(i) - 'a'] = i;
        }

        //j, anchor分别记录当前区间的首尾
        int j = 0, anchor = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < S.length(); i++) {
            //循环遍历S字符串，把每次当前区间的尾索引和代表当前索引所指的字符S.charAt(i)在字符串S中的最后一次的出现的位置last[S.charAt(i) - 'a']作比较，选出最大的，即选出该字符在字符串中最后一次出现的位置
            j = Math.max(j, last[S.charAt(i) - 'a']);
            //遍历到了当前区间的末尾,即 i==j，把当前区间加入答案，同时将 start 设为 i+1 去找下一个区间。
            if (i == j) {
                //ans每次存放的都是符合条件的区间的元素的个数
                ans.add(i - anchor + 1);
                anchor = i + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

    }

}
