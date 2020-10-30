package xyz.zhoupf.primary_algorithm.chuji02_String;

/**
 * 实现 strStr() 函数。
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 *
 * 说明:  KMP算法
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 */
public class ChuJi_String07 {

    public static int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        int[] next = new int[needle.length()];
        getNext(needle, next);
        int j = 0;
        int i = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else j = next[j];
        }
        if (j == needle.length()) return i - j;
        return -1;
    }

    //needle为小字符串
    private static void getNext(String needle, int[] next) {
        next[0] = -1;
        int i = 0;
        int j = -1;
        while (i < needle.length() - 1) {
            //j == -1表明是第一次执行循环体内容
            if (j == -1 || needle.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
    }


    /**
     * strStr()方法的算法没有搞懂，有看了这个稍微简单一点的
     * 1，如果needle为空，则直接返回0。
     * 2，使用循环开始匹配，外层循环遍历haystack的每个字符，内层循环遍历needle的每个字符
     * 3，定义遍历curr记录外层遍历到haystack的哪个索引坐标了
     * 4，内层进行判断，如果curr+needle的长度大于haystack的长度，表明还没有匹配到第一个相同的字符，haystack去掉之前比较过的不相同的字符，然后剩余的长度，装不下needle了，这时就不用比较了，直接返回-1就完事了。
     * 5，如果haystack.charAt(curr)下curr索引的字符不等于needle的第一个字符，那么就直接退出内层循环
     * 6，如果j == needle.length() - 1，即表明循环遍历到了needle的最后一个还没有退出循环表明都是相同的，那么直接返回i就行了
     */
    public static int strStr1(String haystack, String needle) {
        int length = haystack.length();
        if (needle == null || needle.length() == 0) return 0;

        for (int i = 0; i < length; i++) {
            int curr = i;
            for (int j = 0; j < needle.length(); j++) {
                if (i + needle.length() > length) {
                    return -1;
                }
                if (haystack.charAt(curr) != needle.charAt(j)) {
                    break;
                }
                if (j == needle.length() - 1) {
                    return i;
                }
                curr++;
            }
        }
        return -1;
    }

    /**
     * strStr1()的简化版
     * 我们可以根据把haystack分成若干个needle来找
     */
    public static int strStr2(String haystack, String needle) {
        int len = haystack.length();
        int num = needle.length();
        if (len < num || num == 0) {
            return -1;
        }
        int shortLen = len - num;
        for (int i = 0; i < shortLen; i++) {
            if (haystack.substring(i, i + num).equals(needle)) {
                return i;
            }
        }
        return -1;
    }






    public static void main(String[] args) {

    }

}
