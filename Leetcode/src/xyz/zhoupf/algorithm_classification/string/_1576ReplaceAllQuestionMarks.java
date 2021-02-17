package xyz.zhoupf.algorithm_classification.string;

import java.util.HashMap;

/**
 * 给你一个仅包含小写英文字母和 '?' 字符的字符串 s，请你将所有的 '?' 转换为若干小写字母，使最终的字符串不包含任何 连续重复 的字符。
 * 注意：你 不能 修改非 '?' 字符。
 * 题目测试用例保证 除 '?' 字符 之外，不存在连续重复的字符。
 */
public class _1576ReplaceAllQuestionMarks {

    //从前面开始遍历，遇到问号就对比前面和后面的 用一个不与前面和后面一致的字符代替就好了
    public static String modifyString(String s) {
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '?') {
                //前面一个字符  如果当前是第0个的话 字符就为‘ ’
                char ahead = i == 0 ? ' ' : chars[i - 1];
                //后面一个字符  如果当前是最后一个的话 字符就为‘ ’
                char behind  = i == chars.length - 1 ? ' ' : chars[i + 1];
                //从a开始比较  如果等于前面或者后面的话 就+1
                char temp = 'a';
                while (temp == ahead || temp == behind ) {
                    temp++;
                }
                //找到目标字符后 做替换
                chars[i] = temp;
            }
        }
        HashMap<Object, Object> map = new HashMap<>();
        
        return new String(chars);
    }

    public static void main(String[] args) {
        String str = "a?c?b";
        System.out.println(modifyString(str));
        System.out.println(modifyString1(str));
    }

    public static String modifyString1(String s) {
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '?') {
                // 下面两步操作就是寻找当前字符的前一个字符和后一个字符
                char ahead = i == 0 ? ' ' : chars[i - 1];
                char behind = i == chars.length-1 ? ' ' : chars[i + 1];

                char temp = 'a';
                while (ahead == temp || behind == temp) {
                    temp++;
                }
                chars[i] = temp;
            }
        }
        return new String(chars);
    }

}
