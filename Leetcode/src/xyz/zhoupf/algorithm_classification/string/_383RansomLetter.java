package xyz.zhoupf.algorithm_classification.string;

import java.util.Stack;

/**
 * 给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面的字符构成。如果可以构成，返回 true ；否则返回 false。
 *
 * (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。杂志字符串中的每个字符只能在赎金信字符串中使用一次。)
 *
 * canConstruct("aa", "aab") -> true
 */
public class _383RansomLetter {

    public static boolean canConstruct(String ransomNote, String magazine) {
        int[] bucket=new int[26];
        //首先将magazine中各元素分配到桶中
        for (int i=0;i<magazine.length();i++){
            char ch=magazine.charAt(i);
            bucket[ch-'a']++;
        }
        //从桶中取出元素，与ransomNote中的元素匹配
        for (int i=0;i<ransomNote.length();i++){
            char ch=ransomNote.charAt(i);
            if (--bucket[ch-'a']<0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String a= "";
        String b = "a";
        System.out.println(canConstruct(a, b));
    }

}
