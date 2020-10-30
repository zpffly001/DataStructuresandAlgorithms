package xyz.zhoupf.primary_algorithm.chuji02_String;

/**
 * 字符串中的第一个唯一字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 * 提示：你可以假定该字符串只包含小写字母。
 * 示例：
 * s = "leetcode"
 * 返回 0
 * s = "loveleetcode"
 * 返回 2
 */
public class ChuJi_String03 {

    public static int firstUniqChar1(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]);
        }
        char temp;
        int count = 0;//计量和当前字母相同的字母的个数，默认为0
        if (chars.length > 1){
            for (int i = 0; i < chars.length; i++) {
                temp = chars[i];
                for (int j = i+1; j < chars.length; j++) {
                    if (temp == chars[j]){//如果当前字母有相同的则直接退出本次循环
                        break;
                    }
                    if (j == chars.length - 1 && count == 0){//如果循环到了最后还没有相同的，则符合，返回该字母的索引
                        return s.indexOf(temp);
                    }
                }

            }
            return -1;
        }
        if ("".equals(s)){
            return -1;
        }
        return 0;

    }


    public static int firstUniqChar(String s) {
        for (int i = 0; i < s.length(); i++) {
            String s1 = s.substring(i, i + 1);//此处为截取i到i+1，但不包含i+1索引处的值，即取出索引i处的字母。
            String s2 = s.substring(i + 1);//截取索引i+1后的所有字符。
            int firstIndex = s.indexOf(s1);//记录字母s1在字符串s中第一次出现的位置。
            if (!(s2.indexOf(s1) >= 0)){//如果该条件成立就表明在当前字母后面的字符串中，当前该字母再次出现了，即不合格
                System.out.println(s1);
                System.out.println(s.indexOf(s1) );
                return s.indexOf(s1);
            }
        }
        return -1;
    }

    public static int firstUniqChar2(String s) {

        char[] chars = s.toCharArray();
        int len = chars.length;
        //定义数组长度为26，表示26个字母   0-25  分别表示a-z的位置
        int[] arr = new int[26];
        //遍历字符数组，任何一个字母出现一次，都在arr数组对应位置加1
        for (int i = 0; i < s.length(); i++) {
            int chr = chars[i] - 'a';
            arr[chr]++;//每出现一次就把数量加1
        }
        for (int i = 0; i < s.length(); i++) {
            //变量s字符串，如果有字母在arr数组中对应的下标为1，即出现一次
            //那么这个字母就是符合要求的，因此返回这个字母在字符串中对应的索引i
            if(arr[chars[i] - 'a'] == 1) return i;
        }
        return -1;
    }

    /**
     * String a = "e";    String s = "leetcode";
     * s.indexOf(a)是求变量a在字符串s中第一次出现的索引
     * s.substring(3)是截取从索引3开始到最后的字符串，结果为"tcode"
     * s.substring(4, 7)是截取从索引4到索引7结束，但不包含索引7，结果为"cod"
     */
    public static void main(String[] args) {
        String a = "e";
        String s = "leetcode";
        String s1 = "loveleetcode";
        System.out.print(firstUniqChar2(s));
        //System.out.print(firstUniqChar1(s1));
//        测试
//        System.out.println(s.indexOf(a)); 结果为1
//        System.out.println(s.substring(3));   结果为"tcode"
//        System.out.println(s.substring(4, 7));    结果为"cod"
        //System.out.println(Arrays.toString(s.split("")));
    }
}
