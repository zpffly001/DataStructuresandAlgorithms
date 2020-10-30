package xyz.zhoupf.algorithm_classification.double_pointer;

/**
 *  424-替换后的最长重复字符
 */
public class _424LongestRepeatedCharacterAfterReplacement {

    private int[] arr = new int[26];
    /**
     * 滑动窗口
     * 窗口扩张：left不变，right++
     * 窗口滑动：left++, right++
     */
    public int characterReplacement(String s, int k) {
        if (s == null) return 0;
        char[] chars = s.toCharArray();
        int left = 0;
        int right = 0;
        int historyCharMax = 0;
        for (right = 0; right < chars.length; right++) {
            int index = chars[right] - 'A';
            arr[index]++;
            historyCharMax = Math.max(historyCharMax, arr[index]);
            //这里比较窗口大小，即连续相同的元素个数,如果当前重复元素个数大于历史，那么滑动窗口+1
            //right - left + 1是索引，因此要加1，才能表示实际元素个数
            //而right - left + 1 > historyCharMax + k，即窗口大小大于历史重复元素最多的个数，即本次添加的字符和原来连续的字符不一样，
            //从而导致虽然right++,即窗口变大，但是因为和原来连续字符不同，那么historyCharMax并不会加1，因此right - left + 1 > historyCharMax + k
            //因此，滑动窗口中有不同元素，那么窗口要不变，且同步右移，即left++
            if (right - left + 1 > historyCharMax + k){
                //且窗口没向前滑动一次，那么需要把最左边元素，在数组中存储的数量-1，因为后面还可能出现相同的元素，但是不是连续的，总不能在原来的基础上++把
                arr[chars[left] - 'A']--;
                left++;
            }
        }
        return chars.length - left;
    }

}
