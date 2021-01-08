package xyz.zhoupf.algorithm_classification.array;

/**
 * 给你一个整数数组 arr，请你判断数组中是否存在连续三个元素都是奇数的情况：
 * 如果存在，请返回 true ；否则，返回 false
 * 示例 1：
 * 输入：arr = [2,6,4,1]
 * 输出：false
 * 解释：不存在连续三个元素都是奇数的情况。
 */
public class _1550ThreeConsecutiveOddArrays {

    public static boolean threeConsecutiveOdds(int[] arr) {
        int length = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1){
                length++;
            }else if (arr[i] % 2 != 0){
                length++;
            }else {
                length = 0;
            }

            if (length == 3){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }

}
