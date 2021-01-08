package xyz.zhoupf.algorithm_classification.string;

public class _1446ContinuousCharacter {

    public static int maxPower(String s) {
        char[] chars = s.toCharArray();

        int maxLen = 0, curLen = 0;
        if (s.length() == 1) return 1;
        if (s.length()<1 || s==null) return 0;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i-1]){
                curLen++;
            }else {
                maxLen = Math.max(maxLen, curLen);
                curLen = 1;
            }
        }
        return Math.max(curLen, maxLen);
    }

    public static void main(String[] args) {
        String s = "leetcode";
        System.out.println(maxPower(s));
    }

}
/*
* public int maxPower(String s) {
        if (s.length() < 2) return s.length();
        char[] ch_array = s.toCharArray();
        int res = 0, count = 1;
        for (int i = 1; i < ch_array.length; ++i) {
            if (ch_array[i] == ch_array[i - 1])
                ++count;
            else {
                res = Math.max(res, count);
                count = 1;
            }
        }
        return Math.max(res, count);
    }

作者：gfu
链接：https://leetcode-cn.com/problems/consecutive-characters/solution/liang-liang-bi-jiao-xiang-lin-zi-fu-by-gfu/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
