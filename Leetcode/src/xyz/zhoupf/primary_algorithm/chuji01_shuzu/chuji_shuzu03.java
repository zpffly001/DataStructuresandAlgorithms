package xyz.zhoupf.primary_algorithm.chuji01_shuzu;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 */
public class chuji_shuzu03 {
    public static void rotate(int[] nums, int k) {
        /*int len = nums.length;
        int[] nums1 = new int[len];
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < len-k; i++){
            list1.add(nums[i]);
            System.out.println(list1.toString());
        }
        for (int i = len-k; i < len; i++){
            list2.add(nums[i]);
            System.out.println(list2.toString());
        }
        for (int i = 0; i < len; i++){
            if (i<k){
                nums1[i] = (int)list2.get(i);//5.6.7
                System.out.println(nums1[i]);
            }else {
                nums1[i] = (int)list1.get(i);
                System.out.println(nums1[i]);
            }
            System.out.println(nums1[i]);
        }
        for (int i = k; i < len; i++){
            nums[i] = list1.get(i);//5.6.7
            System.out.println(nums[i]);
        }*/

        int len = nums.length;
        int temp[] = new int[len];
        System.arraycopy(nums, 0, temp, 0, len);//把nums数组从零开始长度为len全部复制到从第0位开始的temp数组中去
        k = k % len;
        int t = 0;
        for (int i = 0; i < len; i++ ){
            t = (i+k)%len;//在本例的结果是 t 的结果位：3，4，5，6，0，1，2  而 i 的结果为：0，1，2，3，4，5，6
            System.out.println(t);
            nums[t] = temp[i];
        }
        for(int i=0;i<len;i++){
            System.out.println(nums[i]);
        }



    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7};
        rotate(nums,3);
    }
}
