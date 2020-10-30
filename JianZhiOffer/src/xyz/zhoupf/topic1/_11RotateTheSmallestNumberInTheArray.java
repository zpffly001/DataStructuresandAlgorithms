package xyz.zhoupf.topic1;

/**
 * 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。  
 * 示例 1：输入：[3,4,5,1,2]  输出：1
 * 示例 2：输入：[2,2,2,0,1]  输出：0
 *
 * 重点提示：排序数组的查找问题首先考虑使用 二分法 解决，其可将 遍历法 的 线性级别 时间复杂度降低至 对数级别 。
 * 设 m = (i + j) / 2m=(i+j)/2 为每次二分的中点（ "/" 代表向下取整除法，因此恒有 i \leq m < ji≤m<j ）
 */
public class _11RotateTheSmallestNumberInTheArray {

    public int minArray(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high){
            int pivot = low + (high - low) / 2;
            if (numbers[pivot] < numbers[high]){
                high = pivot;
            }else if (numbers[pivot] > numbers[high]){
                low = pivot + 1;
            }else {
                high -= 1;
            }
        }
        return numbers[low];
    }

}
