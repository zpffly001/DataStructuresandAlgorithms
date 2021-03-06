package xyz.zhoupf.primary_algorithm.chuji09_other;

/**
 * 位1的个数
 * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）。

 * 示例 1：
 * 输入：00000000000000000000000000001011
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 * 示例 2：
 * 输入：00000000000000000000000010000000
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 */
public class chuji01_wei1degeshu {

    //方法 1：循环和位移动, 这个方法比较直接。我们遍历数字的 32 位。如果某一位是 1 ，将计数器加一。
    //显然，任何数字跟掩码 1 进行逻辑与运算，都可以让我们获得这个数字的最低位。检查下一位时，我们将掩码左移一位。
    //于1相与，都为1则为1，否则即为0
    public static int hammingWeight(int n) {
        //记录1的个数
        int bits = 0;
        int mask = 1;
        for (int i = 0; i < 32; i++) {
            if ((n & mask) != 0){
                bits++;
            }
            //无论是否为1，标记位，都进行左移一位
            mask <<= 1;
        }
        return bits;
    }

    //方法 2：位操作的小技巧
    //我们可以把前面的算法进行优化。我们不再检查数字的每一个位，而是不断把数字最后一个 1 反转，并把答案加一。当数字变成 00 的时候偶，我们就知道它没有 1 的位了，此时返回答案。
    //这里关键的想法是对于任意数字 n，将 n 和 n−1 做与运算，会把最后一个 1 的位变成 0 。为什么？考虑 n 和 n−1 的二进制表示。
    //在二进制表示中，数字 nn 中最低位的 11 总是对应 n - 1n−1 中的 00 。因此，将 nn 和 n - 1n−1 与运算总是能把 nn 中最低位的 11 变成 00 ，并保持其他位不变。
    public static int hammingWeight01(int n) {
        int sum = 0;
        while (n != 0){
            sum++;
            n &= (n - 1);
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(hammingWeight01(-3));
    }

}
