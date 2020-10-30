package xyz.zhoupf.top100;

import java.util.HashSet;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:    输入: "abcabcbb"   输出: 3    解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:    输入: "bbbbb"  输出: 1    解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:    输入: "pwwkew"    输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 题解：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/wu-zhong-fu-zi-fu-de-zui-chang-zi-chuan-by-leetc-2/
 *
 */
public class _03LongestSubstringWithoutRepeatingCharacters {

    public static int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        HashSet<Character> occ = new HashSet<>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动，ans表示最大长度
        int rk = -1, ans = 0;
        for (int i = 0; i < n; i++) {
            // 左指针向右移动一格，移除一个字符
            if (i != 0) occ.remove(s.charAt(i - 1));
            while (rk + 1 < n && !occ.contains(s.charAt(rk + 1))){
                occ.add(s.charAt(rk + 1));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            //ans = Math.max(ans, occ.size());
            ans = Math.max(ans, rk - i + 1);//这种方法比上种方法更高效，为什么要加一呢，因为rk,i都是所以，表示的元素有rk - i + 1个元素
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }
}
/**
 * 我们不妨以示例一中的字符串 {abcabcbb}abcabcbb 为例，找出 从每一个字符开始的，不包含重复字符的最长子串，那么其中最长的那个字符串即为答案。对于示例一中的字符串，我们列举出这些结果，其中括号中表示选中的字符以及最长的字符串：
 *
 * 以 {(a)bcabcbb}(a)bcabcbb 开始的最长字符串为 {(abc)abcbb}(abc)abcbb；
 * 以 {a(b)cabcbb}a(b)cabcbb 开始的最长字符串为 {a(bca)bcbb}a(bca)bcbb；
 * 以 {ab(c)abcbb}ab(c)abcbb 开始的最长字符串为 {ab(cab)cbb}ab(cab)cbb；
 * 以 {abc(a)bcbb}abc(a)bcbb 开始的最长字符串为 {abc(abc)bb}abc(abc)bb；
 * 以 {abca(b)cbb}abca(b)cbb 开始的最长字符串为 {abca(bc)bb}abca(bc)bb；
 * 以 {abcab(c)bb}abcab(c)bb 开始的最长字符串为 {abcab(cb)b}abcab(cb)b；
 * 以 {abcabc(b)b}abcabc(b)b 开始的最长字符串为 {abcabc(b)b}abcabc(b)b；
 * 以 {abcabcb(b)}abcabcb(b) 开始的最长字符串为 {abcabcb(b)}abcabcb(b)。
 */
