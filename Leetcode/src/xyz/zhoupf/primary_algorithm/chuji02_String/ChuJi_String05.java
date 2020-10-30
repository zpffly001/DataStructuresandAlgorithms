package xyz.zhoupf.primary_algorithm.chuji02_String;

/**
 * 验证回文字符串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 */
public class ChuJi_String05 {

    /**
     *所谓回文字符串，就是从左往右读和从右往左读都是一样的
     * 大佬的解题思路大致如下
     * 0，在while循环中不断遍历，但首部索引l要一直小于尾部索引r
     * 1，先判断索引到的字符是否在A--->Z这个区间内，因为判断不分大小写，在这里统一变成小写，即经过判断，如果字符原来是大写字母，经变换，首部b[l]和尾部b[r]所遍历到的字符串都变为大写
     * 2，再判断，如果原字符本来就不是A-Z，不能转换为a-z，在这里我们在依次判断首部，尾部字符是否在在0---》9，小写a--->z这两个区间，如果不在，则首部，尾部字符可能是空格等一类其它字符串，大佬这里直接进行索引变化，即忽略掉这些无关字符串。
     * 3，经判断，如果这时字符串还符合条件，那么就表明此首尾字符串，均在0---》9，小写a--->z，这两个区间，我们再对其进行判断，如果两字符不相同则直接返回false，如果相同，索引变化，继续比较其他索引处的首尾字符串是否符合。
     * 4，最后都符合的话，循环结束自动返回true
     * 5，在输入字符串为""或者为null的情况下直接返回true
     * 6，当索引判断到l=r，也表明前面字符串都相同，返回true就完事了。
     * 7，结束
     */
    public static boolean isPalindrome(String s) {
        if(s==""||s==null) return true;
        char[] b = s.toCharArray();
        int l = 0;
        int r = b.length-1;
        while(l<r){
            //首部A--->Z
            if(((int)b[l]>64&&(int)b[l]<91)){
                //变为小写(因为判断时不区分大小写)
                b[l] += 32;
            }
            //尾部A--->Z
            if(((int)b[r]>64&&(int)b[r]<91)){
                //变为小写(因为判断时不区分大小写)
                b[r] += 32;
            }
            //0---》9，小写a--->z，先判断首部遍历到的字母在这两个区间内
            if(((int)b[l]>47&&(int)b[l]<58) || ((int)b[l]>96&&(int)b[l]<123)){
                //再判断尾部遍历到的字母也在这两个区间内
                if(((int)b[r]>47&&(int)b[r]<58) || ((int)b[r]>96&&(int)b[r]<123)){
                    //判断首部，尾部是否相等，如果不等直接返回false
                    if(b[l] != b[r]){
                        return false;
                    }
                    ////判断首部，尾部是否相等，如果相等，首部尾部各自前进一位
                    l++;
                    r--;
                }else r--;//如果尾部是空格等，即尾部遍历到的字母不在这两个区间内，让尾部跳过这个字母，前进一个，即r--;
            }else l++;//同理，如果首部是空格等，即首部遍历到的字母不在这两个区间内，让首部跳过这个字母，前进一个，即l++;
            if(l == r){
                return true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        String s1 = "race a car";
        System.out.println(isPalindrome(s));
        System.out.println(isPalindrome(s1));
    }

}
