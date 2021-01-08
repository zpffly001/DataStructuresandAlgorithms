package xyz.zhoupf.algorithm_classification.string;

import java.util.Stack;

/**
 * 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
 * 示例 1：
 * 输入："ab-cd"
 * 输出："dc-ba"
 */
public class _917ReverseLetters {

    //反转指针---Character.isLetter() 方法用于判断指定字符是否为字母。
    public static String reverseOnlyLetters(String S) {
        StringBuilder ans = new StringBuilder();
        int j = S.length() - 1;
        for (int i = 0; i < S.length(); i++) {
            //如果当前字符是字母，那么进行翻转
            if (Character.isLetter(S.charAt(i))){
                //尾指针j如果指向的不是一个字母，那么指针左移，即j--
                while (!Character.isLetter(j))
                    j--;
                ans.append(S.charAt(j--));
            }else {
                //如果当前字符是字母，那么直接append
                ans.append(S.charAt(i));
            }
        }
        return ans.toString();
    }

    //字母栈
    public String reverseOnlyLetters02(String S) {
        //先遍历字符串入栈，相当于所有字符都进行反序，但是我们只希望字母反序
        Stack<Character> letters = new Stack();
        for (char c: S.toCharArray())
            //把所有是字母的字符压栈，相当于栈中存放的是所有字母的倒序
            if (Character.isLetter(c))
                letters.push(c);

        StringBuilder ans = new StringBuilder();
        //正序遍历字符数组
        for (char c: S.toCharArray()) {
            //如果当前字符是字母，那么从倒序的栈中出栈拿出一个是字母的字符
            if (Character.isLetter(c))
                ans.append(letters.pop());
            //如果当前字符是字母，那么直接append
            else
                ans.append(c);
        }

        return ans.toString();
    }

}
