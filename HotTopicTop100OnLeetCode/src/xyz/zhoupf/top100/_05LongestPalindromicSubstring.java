package xyz.zhoupf.top100;

/**
 * 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 示例 1：输入: "babad" 输出: "bab"   注意: "aba" 也是一个有效答案。
 * 示例 2：输入: "cbbd"  输出: "bb"
 */
public class _05LongestPalindromicSubstring {

    //方法一：暴力匹配 （Brute Force）
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2){
            return s;
        }

        //记录合格的回文字符串的长度
        int maxLen = 1;
        //记录合格的回文字符串的初始索引
        int begin = 0;
        // s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组
        char[] charArray = s.toCharArray();

        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen &&  validPalindromic(charArray, i, j)){
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin+maxLen);
    }
    /**
     * 暴力匹配每一个字符，验证子串 s[left..right] 是否为回文串
     */
    private boolean validPalindromic(char[] charArray, int left, int right) {
        while (left < right){
            if (charArray[left] != charArray[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    //方法二：动态规划
    public String longestPalindrome01(String s) {
        int len = s.length();
        if (len < 2){
            return s;
        }

        //记录合格的回文字符串的长度
        int maxLen = 1;
        //记录合格的回文字符串的初始索引
        int begin = 0;

        // dp[i][j] 表示 s[i, j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        char[] charArray = s.toCharArray();

        //先初始化把布尔数组的每个值都初始化为true
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        for (int j = 1; j < len; j++){
            for (int i = 0; i < j; i++) {//这两层循环，即遍历了j索引前的所有元素的排列组合
                //如果两个字符不相等，则标记为false
                if (charArray[i] != charArray[j]) dp[i][j] = false;
                else {
                    //如果两个字符相等，且j - i < 3，表明判断到了最后，即为回文字符串
                    if (j - i < 3){
                        dp[i][j] = true;
                    }else {//如果两个字符相等，但是还没有判断到最后，那么还要继续判断，如果递归到j - i < 3，也表明判断到了最后，即为回文字符串
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][j] == true 成立，就表示子串 s[i..j] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen){
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }


    public static void main(String[] args) {

    }

}
