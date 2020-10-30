package xyz.zhoupf.primary_algorithm.chuji01_shuzu;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 */
public class chuji_shuzu05 {
    public static int singleNumber(int[] nums) {
//        int num = 0;
//        HashMap<Integer,String> hashMap = new HashMap<>();
//        for (int i = 0; i < nums.length; i++){
//            if (hashMap.get(nums[i]) == null){
//                hashMap.put(nums[i],"1");
//            }else {
//                hashMap.put(nums[i],"2");
//            }
//        }
//
//        for (Integer kk : hashMap.keySet()){
//            if (hashMap.get(kk).equals("1")){
//                num = kk;
//            }
//        }
//        return num; 这种方法比较耗时


        int once = 0;
        for (int i = 0; i < nums.length;i++){
            once = once ^ nums[i];
        }
        return once;

    }

    public static void main(String[] args) {
        int [] nums = {4,1,2,1,2};
        System.out.println(singleNumber(nums));
    }

}
