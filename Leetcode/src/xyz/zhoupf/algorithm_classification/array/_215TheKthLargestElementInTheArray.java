package xyz.zhoupf.algorithm_classification.array;

import java.util.PriorityQueue;

/**
 * 215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素
 * ，而不是第 k 个不同的元素。
 *
 * 示例 1:    输入: [3,2,1,5,6,4] 和 k = 2    输出: 5
 */
public class _215TheKthLargestElementInTheArray {

    public int findKthLargest(int[] nums, int k) {
        //建立优先队列，存储前K大的元素
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int num : nums) {
            if (heap.size() < k)
                heap.add(num); //把优先队列填满
            else if (heap.peek() < num){
                //如果数组中遍历到的数字num大于优先队列中的peek()即最小的，那么把那个小的数字出队
                heap.poll();
                //把这个大的入队，并重新对队列中数字排序
                heap.add(num);
            }
        }
        return heap.poll();
    }

    public static void main(String[] args) {

    }

}
