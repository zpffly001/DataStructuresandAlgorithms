package xyz.zhoupf.algorithm_classification.double_pointer;

/**
 * 删除排序数组中的重复项 II
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * 示例 1:    给定 nums = [1,1,1,2,2,3],
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 * 你不需要考虑数组中超出新长度后面的元素。
 */
public class _80RemoveDuplicatesFromSortedArrayII {

    //方法二：覆盖多余的重复项，即不删除，但是把符合条件的j++
    public static int removeDuplicates(int[] nums) {
        int j = 1, count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]){
                count++;
            }else {//表明当前元素和上一个元素不一样，即遇到了不相同的元素，因此count要还原为1.
                count = 1;
            }

            if (count <= 2){
                nums[j++] = nums[i];//最后以j为索引，即nums[j]为新数组
            }
        }
        return j;
    }

    //方法一：删除多余的重复项（暴力）
    public int removeDuplicates01(int[] nums) {
        int i = 1, count = 1, length = nums.length;
        while (i < length){
            if (nums[i] == nums[i - 1]){
                count++;
                if (count > 2){
                    //表示当前元素重复，需要删除
                    this.remElement(nums, i);
                    i--;//调用方法把i索引处的重复元素删除了，因此也自然要i--
                }
            }else {//表明当前元素和上一个元素不一样，即遇到了不相同的元素，因此count要还原为1.
                count = 1;
            }
            i++;
        }
        return length;
    }
    private int[] remElement(int[] arr, int index){
        for (int i = index + 1; i < arr.length; i++) {
            arr[i - 1] = arr[i];
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3};
        System.out.println(removeDuplicates(nums));
    }

}
