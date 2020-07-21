package sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class Insertion {

    /*
    对数组a中的元素进行排序
    */
    public static void sort(Comparable[] a) {
        //即需要遍历到倒数第一个值，即a.length-1
        //而且这里索引要从1开始，因为我们默认把索引0上的数看作是已排序
        for (int i=1;i<=a.length-1;i++){
            //这里是从i，即未排序的第一个，一次与已经排序的数组(当然这里是倒着和已排序的比较)
            for (int j=i;j>0;j--){
                if (greater(a[j-1], a[j])){//如果已排序中的数大于未排序的数，就交换位置
                    exch(a,j-1,j);
                }else {
                    break;
                }
            }
        }
    }

    /*
    比较v元素是否大于w元素
    由多态性我们可以把Integet类型看作Comparable接口类型
    */
    private static boolean greater(Comparable v,Comparable w){
        return v.compareTo(w) > 0;
    }

    /*
    数组元素i和j交换位置
    这个案例我们传入的数组a[] 中每个元素都是Integet类型，该类型继承了Comparable接口
    重写了comparaTo()方法，因此由多态性我们可以把Integet类型看作Comparable接口类型
    我们这样做的好处就是，后面调用时，只需要继承Comparable接口，重写比较方法就行了，通用性较好
    */
    private static void exch(Comparable[] a,int i,int j){

        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;

    }

}

//测试代码
class TestInsertion {
    public static void main(String[] args) {
        Integer[] a = {4,3,2,10,12,1,5,6};
        Insertion.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
