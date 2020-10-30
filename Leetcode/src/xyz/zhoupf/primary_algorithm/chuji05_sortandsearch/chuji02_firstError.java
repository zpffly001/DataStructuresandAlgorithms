package xyz.zhoupf.primary_algorithm.chuji05_sortandsearch;

/**
 * 第一个错误的版本
 * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
 *
 * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
 *
 * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
 */
public class chuji02_firstError {

    /**
     * 方法一：线性扫描 [超出时间限制]
     * 最直接的方法是进行一次线性扫描，即对 [1..n][1..n] 都调用一次 isBadVersion。
     */
    public int firstBadVersion(int n){
        for (int i = n-1; i >= 0; i++) {
            if (isBadVersion(i))
                return i;
        }
        return n;
    }

    //方法二，二分查找
    public int firstBadVersion01(int n){
        int left = 0;
        int right = n;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)){
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }

    //拓展，数组二分查找指定元素的索引，不存在返回-1
    public static int firstBadVersion01(int[] arr, int n){
        int left = 0;
        int right = arr.length-1;
        int mid = (right - left) / 2;
        while (left < right){
            if (arr[mid] == n) return mid;
            if (arr[mid] > n){
                right = mid;
                mid = (mid - left) / 2;
            }else {
                left = mid + 1;
                mid = left + (right - left) / 2;
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 3, 7, 5};
        System.out.println(firstBadVersion01(arr, 2));
    }

    public boolean isBadVersion(int n){
        return true;
    }

}

