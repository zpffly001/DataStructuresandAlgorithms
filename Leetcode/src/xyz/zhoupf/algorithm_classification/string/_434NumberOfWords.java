package xyz.zhoupf.algorithm_classification.string;

/**
 * 434. 字符串中的单词数统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 *
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 *
 * 示例:  输入: "Hello, my name is John"    输出: 5
 * 解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
 */
public class _434NumberOfWords {

    public static int countSegments(String s) {
        String str = s.trim();
        if (str.equals("")) return 0;
        //public String[] split(String regex)根据给定的正则表达式的匹配来拆分此字符串。
        //  +号表示一个或多个的意思，\\s表示   空格,回车,换行等空白符，因此\\s+表示一个或多个空格，回车，换行等空白符
        return str.split("\\s+").length;
    }

    public static int countSegments02(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            //表示如果i是0索引，且0索引处的值不为空count++
            //然后只有前一个字符为空，当前字符不为空，才会count++
            if ((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' ')
                count++;
        }
        return count;
    }

    public int countSegments03(String s) {
        int segmentCount = 0;

        for (int i = 0; i < s.length(); i++) {
            if ((i == 0 || s.charAt(i-1) == ' ') && s.charAt(i) != ' ') {
                segmentCount++;
            }
        }

        return segmentCount;
    }

    public static void main(String[] args) {

    }

}
