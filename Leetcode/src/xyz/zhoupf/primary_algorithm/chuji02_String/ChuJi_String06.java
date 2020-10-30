package xyz.zhoupf.primary_algorithm.chuji02_String;

/**
 * 字符串转换整数 (atoi)
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
 * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 * 提示：
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 */

/**
 * 分析大佬的解题思路
 * 1，去掉首尾两端的空格
 * 2，字符串为空，或者长度为0，直接返回0
 * 3，取得去掉空字符后的第一个字符，然后定义开始索引start为0，正负号标记符sign默认为1
 * 4，如果第一个字符为'+'或者'-'，则把正负号标记符记为1或-1，，并把起始索引start++
 * 5，判断过正负号后，对余下字符串进行遍历，先调用Character.isDigit() 方法,判断首个字符是否为数字，如果首个字符不是数字，则返回0
 * 6，如果+，-号后面紧跟的就是数字，则先对数字进行变化，最后当后面字符不是数字时，结果*sign，然后返回
 */
public class ChuJi_String06 {

    public static int myAtoi(String str) {
        //去掉首尾两端的空格
        str = str.trim();
        //字符串为空，或者长度为0，直接返回0
        if (str == null || str.length() == 0) return 0;

        //+ - 号
        char firstChar = str.charAt(0);
        int sign = 1;
        int start = 0;
        long res = 0;
        if (firstChar == '+'){
            sign = 1;
            start++;
        }else if (firstChar == '-'){
            sign = -1;
            start++;
        }

        for (int i = start; i < str.length(); i++) {
            //Java Character.isDigit() 方法,判断字符是否为数字
            //判断首个字符是否为数字，如果首个字符不是数字，则返回0
            //判断后面字符如果不是数字，把前面的结果*sign，并返回
            if (!Character.isDigit(str.charAt(i))){
                return (int) (res * sign);
            }
            //判断到这里就表明这里的str.charAt(i)为一个数字
            //然后这步算数的含义是，如果为第一个数字，则res为0，即res值为0-9，如果是后面在来数字，就对其进行原数字进一位(*10)，然后加上要添加的数字
            res = res * 10 + str.charAt(i) - '0';

            //下面两步是对最大值，最小值的判断
            if (sign == 1 && res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign == -1 && res > Integer.MAX_VALUE) return Integer.MIN_VALUE;
        }

        return (int) res * sign;
    }

    public static void main(String[] args) {
        String s = "  4193 with words";
        System.out.println(myAtoi(s));
    }

}
