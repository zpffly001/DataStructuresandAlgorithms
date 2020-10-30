package xyz.zhoupf.primary_algorithm.chuji02_String;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 有效的字母异位词
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 你可以假设字符串只包含小写字母。
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * 输入: s = "rat", t = "car"
 * 输出: false
 */
public class ChuJi_String04 {

    /**
     *我们要了解什么是字母异位词
     * 口水话就是长度一样，所含一样的字母就是字母异位词，由这个思路我们知道，长度不一样那么直接pass，这是一个前提，长度一样才能进行下面的判断。之后就是我们如何判断所含字母一样呢？
     * 方法一：我的想法是用数组，然后进行排列，把他们排列的结果一一对比就行了。
     */
    public static boolean isAnagram(String s, String t) {
        char[] ss = s.toCharArray();//将s字符串转换为数组
        char[] tt = t.toCharArray();//将t字符串转换为数组

        if(ss.length != tt.length) return false;//长度不等，直接pass

        Arrays.sort(ss);//排列组合
        Arrays.sort(tt);

        return Arrays.equals(ss,tt);//看他们各自拼接的组合有无相等的可能
    }

    /**
     * 方法二还没有做出来！！！！！！
     * 方法二：建立一张哈希表，以字母为为key，出现次数为value。先循环遍历一边第一个数组，并把所有元素按照出现次数放入map中，相同字母(key)每出现一次，其对应的value值+1
     * 相反再遍历第二个字符数组，相同字母(key)每出现一次，其对应的value值-1.最后再判断表中每个key所对应的value是否都为0.如果是则两个数组形成字母异位词。
     */
    public static boolean isAnagram1(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        //循环遍历一边第一个数组，并把所有元素按照出现次数放入map中，相同字母(key)每出现一次，其对应的value值+1
        for (int i = 0; i < chars1.length; i++) {
            if (!map.containsKey(chars1[i])){//如果map中还没有对应的key，则把该字母作为key放入map中
                map.put(chars1[i], 1);
            }
            Integer value = map.get(chars1[i]);
            map.put(chars1[i], value + 1);
        }
        //再遍历第二个字符数组，相同字母(key)每出现一次，其对应的value值-1
        for (int i = 0; i < chars2.length; i++) {
            if (map.containsKey(chars2[i])){//如果map中有对应的key，则把该key对应的value-1
                Integer value = map.get(chars2[i]);
                map.put(chars2[i], value - 1);
            }
        }
        for (Character key : map.keySet()) {
            if (map.get(key) != 0){
                System.out.println(map.get(key));
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram(s, t));
        System.out.println(isAnagram1(s, t));
    }

}
