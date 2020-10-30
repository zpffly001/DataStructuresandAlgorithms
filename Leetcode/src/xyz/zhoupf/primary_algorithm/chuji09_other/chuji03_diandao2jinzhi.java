package xyz.zhoupf.primary_algorithm.chuji09_other;

/**
 * 异或xor ^ 如果a、b两个值不相同，则异或结果为1。如果a、b两个值相同，异或结果为0
 * 与&运算，二进制每位，只有是1，&的结果才是1，否则为0，即与运算，两个数同时为1的时候才为1
 * i&1, 你可以随便取一个奇数，转成二进制最后一位肯定是1，因为前面几位即便为1，也是2的倍数，即奇数，则最右一位必定为1.
 * i&1, 取当前 i 的最后一位
 */
public class chuji03_diandao2jinzhi {

    /**
     * 解题思路
     * 取当前 n 的最后一位：n & 1
     * 将最后一位移动到对应位置，第一次为 31 位，第二次是 30 位，即：31、30、29... 1、0，写作代码 bit << 31;
     * 退出条件，二进制反转时，如果剩余位数全位 0，则可以不用再反转。
     */
    public static int reverseBits(int n) {
        int ans = 0;
        //>>> 表示无符号右移，也叫逻辑右移，即若该数为正，则高位补0，而若该数为负数，则右移后高位同样补0
        for (int bitsSize = 31; n != 0; n >>>= 1, bitsSize--){
            //取当前 n 的最后一位：n & 1, 且用新变量ans存储，n相当于每次只把最后一位移出去。
            ans += (n & 1) << bitsSize;
        }
        return ans;
    }

    public static void main(String[] args) {

    }

}
