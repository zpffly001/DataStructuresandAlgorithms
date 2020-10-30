package xyz.zhoupf.primary_algorithm.chuji09_other;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 2:            示例 3:           示例 4:           示例 5:
 * 输入: "()[]{}"     输入: "(]"        输入: "([)]"      输入: "{[]}"
 * 输出: true         输出: false       输出: false       输出: true
 */
public class chuji05_youxiaodesanjiao {

    public static boolean isValid(String s) {
        int n = s.length();
        //n为奇数，说明肯定有一个左括号闭合不了，直接返回false就完事了。
        if (n % 2 == 1) return false;

        HashMap<Character, Character> pairs = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            //map中的key都是右括号
            if (pairs.containsKey(ch)){
                //这时拿着此右括号和栈顶的相比，如果栈顶不是同类型的左括号，或者栈为空，直接返回false就完事了。
                if (stack.isEmpty() || stack.peek() != pairs.get(ch)) return false;
                //如果相同，则把栈顶元素出栈，继续下轮循环，新的栈顶元素和新的右括号再比较
                stack.pop();
            }else stack.push(ch);//左括号则先入栈
        }
        return stack.isEmpty();//最后如果栈为空，表明正好左右括号匹配完毕，返回true
    }

    public static void main(String[] args) {
        System.out.println(isValid("{[]}"));
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("([)]"));
    }

}
