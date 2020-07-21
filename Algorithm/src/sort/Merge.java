package sort;

import java.util.Arrays;

public class Merge {

    private static Comparable[] assist;//归并所需要的辅助数组
    /*
    对数组a中的元素进行排序
    */
    public static void sort(Comparable[] a) {
        assist = new Comparable[a.length];
        int lo = 0;
        int hi = a.length-1;
        sort(a, lo, hi);
    }

    /*
    对数组a中从lo到hi的元素进行排序
    */
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        //对lo到mid之间的元素进行排序；
        sort(a, lo, mid);
        //对mid+1到hi之间的元素进行排序；
        sort(a, mid+1, hi);
        //对lo到mid这组数据和mid到hi这组数据进行归并
        merge(a, lo, mid, hi);
    }

    /*
    对数组中，从lo到mid为一组，从mid+1到hi为一组，对这两组数据进行归并
    */
    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        //定义三个指针
        int i = lo;
        int p1 = lo;
        int p2 = mid + 1;

        //遍历，移动p1指针和p2指针，比较这两个索引对应的值，找出较小的那个，直到这个两个数组中的其中一个数组遍历完
        while (p1<=mid && p2<=hi){
            //比较两个索引对应的值
            if (less(a[p1], a[p2])){//如果p1指针对应的值小
                assist[i++] = a[p1++];//则把p1指针对应的值，放入临时数组，然后p1指针后移，临时数组指针i后移
            }else {//如果p2指针对应的值小或者等于
                assist[i++] = a[p2++];//则把p1指针对应的值，放入临时数组，然后p1指针后移，临时数组指针i后移
            }
        }

        //经过上面一次while循环，两个小数组必定有一个指针到了尽头，而另外一个指针没有走到尽头，因此我们需要把没有走到尽头的指针继续走，直到走到尽头，并依次取出其中的值，放入临时数组
        //因为我们在Sort()重载方法中，递归的调用了Sort()方法，因此每个小数组都是有序的，由此得出，指针没有走到尽头的小数组，没有遍历的数都是比较大的，依次取出放入临时数组就行了

        while (p1 <= mid){
            assist[i++] = a[p1++];
        }

        while (p2 <= hi){
            assist[i++] = a[p2++];
        }

        //以上两个while循环只会执行一个，因为只会有一个数组的指针没有走到尽头

        //把辅助数组中的数据拷贝到原数组
        for (int index=lo; index<=hi; index++){
            a[index] = assist[index];
        }
    }


    /*
    比较v元素是否小于w元素
    */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
    /*
    数组元素i和j交换位置
    */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}

//测试代码
class TestMerge {
    public static void main(String[] args) throws Exception {
        Integer[] arr = {9, 8, 4, 5, 7, 1, 3, 6, 2};
        Merge.sort(arr);
        System.out.println(Arrays.toString(arr));
    }

}
