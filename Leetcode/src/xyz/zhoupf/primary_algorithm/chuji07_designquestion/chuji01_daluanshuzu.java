package xyz.zhoupf.primary_algorithm.chuji07_designquestion;

import java.util.Random;

/**
 * 打乱数组
 * 打乱一个没有重复元素的数组。
 * 示例:
 * // 以数字集合 1, 2 和 3 初始化数组。
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 * // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
 * solution.shuffle();
 * // 重设数组到它的初始状态[1,2,3]。
 * solution.reset();
 * // 随机返回数组[1,2,3]打乱后的结果。
 * solution.shuffle();
 */
public class chuji01_daluanshuzu {

    //暴力解法
//    private int[] array;
//    private int[] original;
//
//    private Random rand = new Random();
//
//    private List<Integer> getArrayCopy() {
//        List<Integer> asList = new ArrayList<Integer>();
//        for (int i = 0; i < array.length; i++) {
//            asList.add(array[i]);
//        }
//        return asList;
//    }
//
//    public chuji01_daluanshuzu(int[] nums) {
//        array = nums;
//        original = nums.clone();
//    }
//
//    public int[] reset() {
//        array = original;
//        original = original.clone();
//        return array;
//    }
//
//    public int[] shuffle() {
//        List<Integer> aux = getArrayCopy();
//
//        for (int i = 0; i < array.length; i++) {
//            int removeIdx = rand.nextInt(aux.size());
//            array[i] = aux.get(removeIdx);
//            aux.remove(removeIdx);
//        }
//
//        return array;
//    }
    
    //方法二： Fisher-Yates 洗牌算法
    private int[] array;
    private int[] original;

    Random rand = new Random();

    private int randRange(int min, int max) {
        return rand.nextInt(max - min) + min;
    }

    private void swapAt(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public chuji01_daluanshuzu(int[] nums) {
        array = nums;
        original = nums.clone();
    }

    public int[] reset() {
        array = original;
        original = original.clone();
        return original;
    }

    public int[] shuffle() {
        for (int i = 0; i < array.length; i++) {
            swapAt(i, randRange(i, array.length));
        }
        return array;
    }

}
