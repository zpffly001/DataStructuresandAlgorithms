package xyz.zhoupf.primary_algorithm.chuji02_String;

/**
 * 整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。就是最大为Int类型，4字节
 * 输入: -123
 * 输出: -321
 *
 * 输入: 120
 * 输出: 21
 */
public class ChuJi_String02 {
    public static int reverse(int x) {

        int maxValue = Integer.MAX_VALUE;//获得int类型最大数
        int minValue = Integer.MIN_VALUE;//获得int类型的最小数
        if (x > maxValue) return 0;
        if (x < minValue) return 0;
        if (x == 0) return 0;
        long newnum = 0;
        while (x != 0){
            newnum = newnum*10 + x%10;//每进行一次循环，都会把原数x的最后一位取出，放到newnum的末尾。而每次循环newnum也会把上次追加到末尾的数据进位(*10)
            x=x/10;//每次循环都去除x的最后一位，因为最后一位数已经加载了newnum上
        }
        if(newnum > maxValue || newnum < minValue) return 0;
        return (int) newnum;


//        int INT_MAX =Integer.MAX_VALUE;          //获取int的最大值2147483647
//        int INT_MIN =Integer.MIN_VALUE;            //获取int的最小值-2147483648
//        if(x>INT_MAX) return 0;
//        if(x<INT_MIN) return 0;
//        if(x==0) return 0;
//        long newnum=0;
//        while(x!=0){
//            newnum=newnum*10+x%10;
//            x=x/10;
//        }
//        if(newnum>INT_MAX||newnum<INT_MIN) return 0;
//        return (int) newnum;
    }

    public static void main(String[] args) {
        System.out.println(reverse(-120));
    }

}
