package xyz.zhoupf.top100;

/**
 * 寻找两个正序数组的中位数
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 *
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * 示例 1:    nums1 = [1, 3]   nums2 = [2]  则中位数是 2.0
 * 示例 2:    nums1 = [1, 2]   nums2 = [3, 4]   则中位数是 (2 + 3)/2 = 2.5
 */
public class _04TheMedianOfTheArray {

    //解法一，暴力解法。先将两个数组合并，两个有序数组的合并也是归并排序中的一部分。然后根据奇数，还是偶数，返回中位数。
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] nums;
        int m = nums1.length;
        int n = nums2.length;
        nums = new int[m + n];
        //这两种分别是其中一个数组为空的特殊情况。
        if (m == 0){
            if (n % 2 == 0){
                return (nums2[n / 2 - 1] + nums2[n / 2]) / 2.0;
            }else return nums2[n / 2];
        }
        if (n == 0){
            if (m % 2 == 0){
                return (nums1[m / 2 - 1] + nums1[m / 2]) / 2.0;
            }else return nums1[m / 2];
        }

        //这里是两个数组都不为空的情况
        int count = 0;
        //i代表遍历到的nums1的索引，j代表遍历到的nums2的索引。
        int i = 0, j = 0;
        //count表示合并的数组nums，即最大所以不能超过或等于两个数组长度之和，即合并数组后最大索引等于数组长度-1.
        while (count != (m + n)){
            //i == m表明数组nums1已经遍历完毕，然后直接把nums2中的数据依次放入数组nums，最后退出while循环
            if (i == m){
                while (j != n){
                    nums[count++] = nums2[j++];
                }
                break;
            }
            //i == m表明数组nums2已经遍历完毕，然后直接把nums1中的数据依次放入数组nums，最后退出while循环
            if (j == n){
                while (i != m){
                    nums[count++] = nums1[i++];
                }
                break;
            }
            //表明nums1和nums2都没有遍历完毕，要比较nums1[i] < nums2[j]的大小，小的放入，索引++。
            if (nums1[i] < nums2[j]){
                nums[count++] = nums1[i++];
            }else {
                nums[count++] = nums2[j++];
            }
        }
        //表明数组已经合并完毕，返回中位数
        if (count % 2 == 0){
            return (nums[count / 2 - 1] + nums[count / 2]) / 2.0;
        }else {
            return nums[count / 2];
        }
    }

    //解法二，暴力解法。其实，我们不需要将两个数组真的合并，我们只需要找到中位数在哪里就可以了。
    public double findMedianSortedArrays01(int[] A, int[] B) {
        //m和n分别代表数组A，B的长度，len代表数组合并后的总长度。
        int m = A.length;
        int n = B.length;
        int len = m + n;
        int left = -1, right = -1;
        int aStart = 0, bStart = 0;
        //从0->(len/2)一共循环了(len/2+1)次，
        for (int i = 0; i <= len / 2; i++) {
            //每次right在得到最新的值前，都会把上次循环得到的值赋给left。最后right是第len/2+1个数，left是第len/2和个数
            left = right;
            //必须条件是指向a数组的指针aStart小于a数组长度m，即没有越界。
            //剩下条件就是如果a数组当前aStart索引的数小于b数组当前bStart索引的数，或者bStart >= n即b数组已经取完了
            if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
                right = A[aStart++];
            } else {
                right = B[bStart++];
            }
        }
        //如果长度为偶数，则返回left + right两数的和的一半
        if ((len & 1) == 0)
            return (left + right) / 2.0;
        else
            //如果长度为奇数个，直接返回中间的那个树，即right，即第len/2+1的索引位置的数。
            return right;
    }

    //解法三：二分查找的方法查找中位数，使时间复杂度达到O(log(m+n)O(log(m+n)
    public double findMedianSortedArrays02(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        //因为数组是从索引0开始的，因此我们在这里必须+1，即索引(k+1)的数，才是第k个数。
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }
    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        //因为索引和算数不同6-0=6，但是是有7个数的，因为end初始就是数组长度-1构成的。
        //最后len代表当前数组(也可能是经过递归排除后的数组)，符合当前条件的元素的个数
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        //就是如果len1长度小于len2，把getKth()中参数互换位置，即原来的len2就变成了len1，即len1，永远比len2小
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        //如果一个数组中没有了元素，那么即从剩余数组nums2的其实start2开始加k再-1.
        //因为k代表个数，而不是索引，那么从nums2后再找k个数，那个就是start2 + k-1索引处就行了。因为还包含nums2[start2]也是一个数。因为它在上次迭代时并没有被排除
        if (len1 == 0) return nums2[start2 + k - 1];

        //如果k=1，表明最接近中位数了，即两个数组中start索引处，谁的值小，中位数就是谁(start索引之前表示经过迭代已经被排出的不合格的元素，即数组没被抛弃的逻辑上的范围是nums[start]--->nums[end])。
        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        //为了防止数组长度小于 k/2,每次比较都会从当前数组所生长度和k/2作比较，取其中的小的(如果取大的，数组就会越界)
        //然后素组如果len1小于k / 2，表示数组经过下一次遍历就会到末尾，然后后面就会在那个剩余的数组中寻找中位数
        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        //如果nums1[i] > nums2[j]，表示nums2数组中包含j索引，之前的元素，逻辑上全部淘汰，即下次从J+1开始。
        //而k则变为k - (j - start2 + 1)，即减去逻辑上排出的元素的个数(要加1，因为索引相减，相对于实际排除的时要少一个的)
        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }


}
