package xyz.zhoupf.algorithm_classification.double_pointer;

/**
 * 925- 长按键入
 * 你的朋友正在使用键盘输入他的名字 name。偶尔，在键入字符 c 时，按键可能会被长按，而字符可能被输入 1 次或多次。
 *
 * 你将会检查键盘输入的字符 typed。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回 True。
 * 示例 1：输入：name = "alex", typed = "aaleex"  输出：true
 * 解释：'alex' 中的 'a' 和 'e' 被长按。
 * 示例 2：输入：name = "saeed", typed = "ssaaedd"输出：false
 * 解释：'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
 */
public class _925LongPressToType {

    public boolean isLongPressedName(String name, String typed) {
        char[] a = name.toCharArray();
        char[] b = typed.toCharArray();
        int len1 = a.length;
        int len2 = b.length;

        int indexA=0,indexB=0;
        if (a[indexA] != b[indexB] || a[len1 - 1] != b[len2 - 1]) return false;

        for (int i = 0; i < len2; i++) {
            if (a[indexA] == b[indexB]){
                indexA = indexA == len1 - 1 ? len1 - 1 : ++indexA;// 防止A越界
                indexB++;
            }else {
                if (a[indexA - 1] == b[indexB]){
                    indexB++;
                }else {
                    return false;
                }
            }
        }
        return true;
    }

}
