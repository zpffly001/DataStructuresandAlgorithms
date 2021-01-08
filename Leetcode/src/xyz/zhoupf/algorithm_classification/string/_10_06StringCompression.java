package xyz.zhoupf.algorithm_classification.string;

/**
 * 面试题 01.06. 字符串压缩
 * 字符串压缩。利用字符重复出现的次数，编写一种方法，实现基本的字符串压缩功能。比如，字符串aabcccccaaa会变为a2b1c5a3。若“压缩”后的字符串没有变短，则返回原先的字符串。你可以假设字符串中只包含大小写英文字母（a至z）。
 *
 * 示例1:
 *  输入："aabcccccaaa"
 *  输出："a2b1c5a3"
 */
public class _10_06StringCompression {

    public static String compressString(String S) {
        if (S.length() == 0) { // 空串处理
            return S;
        }
        StringBuffer ans = new StringBuffer();
        int cnt = 1;
        char ch = S.charAt(0);
        for (int i = 1; i < S.length(); i++) {
            if (ch == S.charAt(i)){
                //表示如果当前字符和当前统计的字符相等的话，当前统计的字符的长度加1
                cnt++;
            }else {
                //表示当前字符和当前统计的字符不相等
                ans.append(ch);//把上一个统计的字符加入Buffer
                ans.append(cnt);//紧跟着上一个统计的字符后面加上上一个字符一共出现了多少次
                ch = S.charAt(i);//把要统计的字符换成当前最新的
                cnt = 1;//把当前最新的字符出现的次数重置为1
            }
            //这种情况是把最后统计的那个字符加入Buffer，因为cnt++跳出循环，无法加入Buffer
            //主要是防止最后一个字符出现的次数只有一次，那么就不会加入Buffer
            //这时需要我们手动，加入
            //但是也可能出现了多次，也需要加入Buffer
            ans.append(ch);
            ans.append(cnt);
        }

        return ans.length() >= S.length() ? S : ans.toString();
    }

    public static String compressString02(String S) {
        if (S.length() == 0) { // 空串处理
            return S;
        }
        StringBuffer ans = new StringBuffer();
        int cnt = 1;
        char ch = S.charAt(0);
        for (int i = 1; i < S.length(); ++i) {
            if (ch == S.charAt(i)) {
                cnt++;
            } else {
                ans.append(ch);
                ans.append(cnt);
                ch = S.charAt(i);
                cnt = 1;
            }
        }
        ans.append(ch);
        ans.append(cnt);
        return ans.length() >= S.length() ? S : ans.toString();
    }

    public static void main(String[] args) {
        String s = "aabcccccaaa";
        System.out.println(compressString(s));
    }

}
