package xyz.zhoupf.primary_algorithm.chuji08_math;

import java.util.ArrayList;
import java.util.List;

/**
 * Fizz Buzz
 * 写一个程序，输出从 1 到 n 数字的字符串表示。
 *
 * 1. 如果 n 是3的倍数，输出“Fizz”；
 * 2. 如果 n 是5的倍数，输出“Buzz”；
 * 3.如果 n 同时是3和5的倍数，输出 “FizzBuzz”。
 */
public class chuji01_FizzBuzz {

    public static List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 3 != 0 && i % 5 != 0) list.add(String.valueOf(i));
            else if (i % 3 == 0 && i % 5 != 0) list.add("Fizz");
            else if (i % 3 != 0 && i % 5 == 0) list.add("Buzz");
            else list.add("FizzBuzz");
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> list = fizzBuzz(15);
        for (String s : list) {
            System.out.println(s);
        }
    }

}
