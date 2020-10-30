package xyz.zhoupf.algorithm_classification.greedyalgorithm;

import java.util.Stack;

/**
 * 392. 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * 示例 1:
 * s = "abc", t = "ahbgdc"  返回 true.
 * 示例 2:
 * s = "axc", t = "ahbgdc"  返回 false.
 */
public class _392JudgingSubsequence {

    public static boolean isSubsequence(String s, String t) {
        if (s==null || s.length()<=0) return true;
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        Stack<Character> stack = new Stack<>();
        //倒叙压栈
        for (int i = s.length() - 1; i >= 0; i--)
            stack.push(sArray[i]);

        for (int i = 0; i < t.length(); i++) {
            if (t.indexOf(stack.peek(), i) != -1){
                i = t.indexOf(stack.peek(), 1);
                stack.pop();
            }else {
                return false;
            }
            /*if (tArray[i] == stack.peek()){
                stack.pop();
            }*/
        }
        return stack.isEmpty();
    }
    public boolean isSubsequence2(String s, String t) {
        int n = s.length(), m = t.length();
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == n;
    }


    public static void main(String[] args) {
        String s = "axc";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s, t));
        //System.out.println("ahcbgdccc".indexOf("cdf"));
    }

}
