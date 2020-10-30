package xyz.zhoupf.primary_algorithm.chuji02_String;

/**
 * 反转字符串的三种方法
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 */
public class ChuJi_String01 {

    //只在给定的那个数组中操作

    public static void reverseString(char[] s) {
        int length = s.length;
        for (int i = 0; i < length / 2; i++) {
            char temp;
            temp = s[i];
            s[i] = s[length - i - 1];
            s[length - i - 1] = temp;
        }

//        char temp;
//        int length = s.length;
//        for (int i = 0 ,j = length -1; i < length / 2 ; i++,j--) {
//            temp = s[i];
//            s[i] = s[j];
//            s[j] = temp;
//        }
    }

    //倒序遍历字符数组添加到StringBuilder，然后再输出
    public static void reverseString1(char[] s) {
        int length = s.length;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = length - 1; i >= 0; i--) {
            stringBuilder.append(s[i]);
        }
        System.out.println(stringBuilder.toString());
    }

    //调用StringBuilder的API来进行字符串的反转
    public static void reverseString2(char[] s) {
        StringBuilder stringBuilder = new StringBuilder(String.valueOf(s));
        System.out.println(stringBuilder.reverse().toString());
    }

    public static void main(String[] args) {
        char[] s = new char[]{'h','e','l','l','o'};
        reverseString(s);
        for (int i = 0; i < s.length; i++) {
            System.out.println(s[i]);
        }

        char[] s1 = new char[]{'h','e','l','l','o'};
        reverseString1(s1);

        char[] s2 = new char[]{'h','e','l','l','o'};
        reverseString1(s2);
    }


}
