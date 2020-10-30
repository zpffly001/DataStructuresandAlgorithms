package xyz.zhoupf.primary_algorithm.chuji09_other;

/**
 * 汉明距离
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * 两个整数之间的汉明距离是对应位置上数字不同的位数。根据以上定义，提出一种 XOR 的位运算(异或)，当且仅当输入位不同时输出为 1。然后统计结果中等于 1 的位数。
 */
public class chuji02_hanmingjuli {

    //方法一：内置位计数功能,根据以上定义，提出一种 XOR 的位运算，当且仅当输入位不同时输出为 1。(异或，不同为1，相同为0)
    //计算 x 和 y 之间的汉明距离，可以先计算 x XOR y，然后统计结果中等于 1 的位数。
    //大多数编程语言中，都存在各种内置计算等于 1 的位数函数。如果这是一个项目中的问题，应该直接使用内置函数，而不是重复造轮子
    public static int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }

    //方法二：移位
    //这里采用右移位，每个位置都会被移动到最右边。移位后检查最右位的位是否为 1 即可。
    // 检查最右位是否为 1，可以使用取模运算（i % 2）或者 AND 操作（i & 1），这两个操作都会屏蔽最右位以外的其他位。
    //i%2，是因为，除了最右边的数，其它只要是1一定是2的倍数，定能被2整除，因此，如果i%2==1的话，表示最右边的数为1.
    //i&1, 是因为，二进制每位，只有是1，&的结果才是1，否则为0
    // 即与运算，两个数同时为1的时候才为1，你可以随便取一个奇数，转成二进制最后一位肯定是1，因为前面几位即便为1，也是2的倍数，即奇数，则最右一位必定为1.
    public static int hammingDistance01(int x, int y) {
        int xor = x ^ y;
        int distance = 0;
        while (xor != 0){
            if (xor % 2 == 1){//此时为奇数，最右一位必定为1，distance++;
                distance += 1;
            }
            xor >>= 1;
        }
        return distance;
    }

    //方法三：布赖恩·克尼根算法
    //方法二是逐位移动，逐位比较边缘位置是否为 1。寻找一种更快的方法找出等于 1 的位数。是否可以像人类直观的计数比特为 1 的位数，跳过两个 1 之间的 0。例如：10001000。
    //上面例子中，遇到最右边的 1 后，如果可以跳过中间的 0，直接跳到下一个 1，效率会高很多。
    //上面例子中，遇到最右边的 1 后，如果可以跳过中间的 0，直接跳到下一个 1，效率会高很多。
    //这是布赖恩·克尼根位计数算法的基本思想。该算法使用特定比特位和算术运算移除等于 1 的最右比特位。
    //当我们在 number 和 number-1 上做 AND 位运算时，原数字 number 的最右边等于 1 的比特会被移除。
    public static int hammingDistance02(int x, int y) {
        int xor = x ^ y;
        int distance = 0;
        while (xor != 0){
            distance += 1;
            //移除xor最右边的那位'1'
            xor &= xor - 1;
        }
        return distance;
    }

    public static void main(String[] args) {
        System.out.println(hammingDistance(9, 12));
        System.out.println(hammingDistance01(9, 12));
        System.out.println(hammingDistance02(9, 12));

    }

}
