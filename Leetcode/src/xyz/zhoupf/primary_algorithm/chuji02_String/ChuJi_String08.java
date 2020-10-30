package xyz.zhoupf.primary_algorithm.chuji02_String;

/**
 * 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * 所有输入只包含小写字母 a-z 。
 */

/**
 * 1，如果给的字符串数组为空直接返回""
 * 2，因为求得是公共最长前缀，每个字符串的开头都会包含公共前缀，因此我们就拿字符串数组的第一个字符串作为例子
 * 3，循环遍历每个字符串，然后进行while循环，循环条件是每个字符串都不已str这个例子开头，暗含如果每个字符串都已str开头，则表明str就是公共前缀
 * 4，我们在while循环中，每执行一次循环体，就把str从后面去掉一个字符，直到跳出循环，表明找到了最长前缀
 * 5，如果没有跳出while循环，反而str的长度减的只剩下1个元素了，这时返回为空，即没有公共前缀
 */
public class ChuJi_String08 {


    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0  ) return "";
        String str = strs[0];
        for (String s : strs) {
            //表示，直到找到最长前缀才会跳出循环
            while (!s.startsWith(str)){
                if (str.length() == 1) {
                    return "";
                }
                //如果str还不是公共最长前缀，则每次减一在比较
                str = str.substring(0, str.length() - 1);
            }
        }
        return str;
    }

    public static String longestCommonPrefix01(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String str = strs[0];
        for (String s : strs
        ) {
            //表示，直到找到最长前缀才会跳出循环
            while (!s.startsWith(str)){
                if (str.length() == 1){
                    return "";
                }
            }
            //如果str还不是公共最长前缀，则每次减一在比较
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        String[] strs1 = {"a", "b"};
        System.out.println(longestCommonPrefix(strs));
    }

}
