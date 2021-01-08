package xyz.zhoupf.algorithm_classification.string;

/**
 * 给定两个由小写字母构成的字符串 A 和 B ，只要我们可以通过交换 A 中的两个字母得到与 B 相等的结果，就返回 true ；否则返回 false 。
 *
 * 交换字母的定义是取两个下标 i 和 j （下标从 0 开始），只要 i!=j 就交换 A[i] 和 A[j] 处的字符。例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。
 *
 * 示例 1：
 * 输入： A = "ab", B = "ba"   输出： true
 * 解释： 你可以交换 A[0] = 'a' 和 A[1] = 'b' 生成 "ba"，此时 A 和 B 相等。
 */
public class _859IntimacyString {

    public static boolean buddyStrings(String A, String B) {
        if(A.length() != B.length()) return false;
        //在A和B完全相同的情况下，A和B中必须要有1个以上的重复字符，如："aab"和"aab"这样交换两个a无论如何还是相等，因此符合条件返回true
        if (A.equals(B)) {
            //申请容量为26的数组，每个索引所对应的值初始化都为0
            int[] count = new int[26];
            for (int i = 0; i < A.length(); i++)
                //遍历小写字符串A，把各个字符减去'a'得到一个小于等于26的数字，并让对应索引的数组对应的值+1.
                count[A.charAt(i) - 'a']++;
            //表明如果数组中存入了字符，
            //表示在A和B相等的情况下，A和B中有1个以上的重复字符，如："aab"和"aab"这样交换两个a无论如何还是相等，因此符合条件返回true
            for (int c : count)
                if (c > 1) return true;
            return false;
        }else {//在A和B不相同的情况下，A,B只能有两个字符在不同位置
            int first = -1,second = -1;
            for (int i = 0; i < A.length(); i++) {
                if (A.charAt(i) != B.charAt(i))
                    first = i;
                else if (A.charAt(i) != B.charAt(i))
                    second = i;
                else
                    return false;
            }
        }
        return true;
    }

    public static boolean buddyStrings02(String A, String B) {
        if(A.length() != B.length()) return false;
        //在A和B完全相同的情况下，A和B中必须要有1个以上的重复字符，如："aab"和"aab"这样交换两个a无论如何还是相等，因此符合条件返回true
        if (A.equals(B)) {
            //申请容量为26的数组，每个索引所对应的值初始化都为0
            int[] count = new int[26];
            for (int i = 0; i < A.length(); i++)
                //遍历小写字符串A，把各个字符减去'a'得到一个小于等于26的数字，并让对应索引的数组对应的值+1.
                count[A.charAt(i) - 'a']++;
            //表明如果数组中存入了字符，
            //表示在A和B相等的情况下，A和B中有1个以上的重复字符，如："aab"和"aab"这样交换两个a无论如何还是相等，因此符合条件返回true
            for (int c : count)
                if (c > 1) return true;
            return false;
        }else {
            int first = -1, second = -1;
            for (int i = 0; i < A.length(); ++i) {
                if (A.charAt(i) != B.charAt(i)) {
                    if (first == -1)
                        first = i;
                    else if (second == -1)
                        second = i;
                    else //走到这表示有两个以上字符不相等，直接返回false，因为交换2个字符A和B也不会相同
                        return false;
                }
            }

            //最后表示正好有两个或者两个一下的字符不相等
            return (second != -1 && A.charAt(first) == B.charAt(second) &&
                    A.charAt(second) == B.charAt(first));
            //second != -1表示正好有两个字符不相等，且这两个不相等的字符正好互换位置后相等了。
        }
    }

    public static void main(String[] args) {
        String a = "aa";
        String b = "bb";
        //System.out.println(buddyStrings(a, b));
        int[] count = new int[26];
        for (int i : count) {
            System.out.println(i);
        }
    }

}
