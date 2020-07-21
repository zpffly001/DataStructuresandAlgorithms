package sort;

import java.util.Arrays;

public class Quick {

    public static void sort(Comparable[] a) {
        int lo = 0;
        int hi = a.length - 1;
        sort(a, lo, hi);
    }

    public static void sort(Comparable[] a, int lo, int hi){
        //安全性校验
        if (hi<=lo){
            return;
        }

        //对a数组中，从lo到hi的元素进行切分
        int partition = partition(a, lo, hi);//返回的是分组的分界值所在的索引，原本未排序的数组的第一个元素作为分界值，大的放前面，小的放后面，因此排序完分界值的索引不再是原来的索引0

        //对左边分组中的元素进行排序
        sort(a, lo, partition-1);

        //对右边分组中的元素进行排序
        sort(a, partition+1, hi);

    }

    //切分，大的放右边，小的放左边，并交换分界值索引
    public static int partition(Comparable[] a, int lo, int hi){
        Comparable key = a[lo];//把最左边的元素当作分界值
        int left = lo;//定义左侧指针，指向最左边元素
        int right = hi+1;//定义一个右指针，指向最右侧元素的下一个位置

        //进行切分
        while (true){
            //先从右往左扫描，找到一个比基准值小的元素
            while (less(key,a[--right])){//如果循环停止，则说明找到了一个比分界值key小的元素
                if (right == lo){
                    break;//已经扫描到最左边，无法继续扫描
                }
            }

            //再从左往右扫描，找一个比基准值大的元素
            while(less(a[++left],key)){//循环停止，证明找到了一个比基准值大的元素
                if (left==hi){
                    break;//已经扫描到了最右边了，无需继续扫描
                }
            }

            if (left >= right){
                //扫描完了所有元素，结束循环
                break;
            }else {
                //交换left和right索引处的元素
                exch(a, left, right);
            }
        }

        //交换最后right索引处和基准值所在的索引处的值
        exch(a,lo,right);

        return right;
    }

    /*
    数组元素i和j交换位置
    */
    private static void exch(Comparable[] a, int i, int j) {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    /*
    比较v元素是否小于w元素
    */
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }
}

//测试代码
class TestQuick {
    public static void main(String[] args) throws Exception {
        Integer[] arr = {6, 1, 2, 7, 9, 3, 4, 5, 8};
        Quick.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
