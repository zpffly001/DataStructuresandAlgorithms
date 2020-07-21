package sort;

import java.util.Arrays;

/**
 * 冒泡排序，如果给一组数从小到大或者从大到小排序，那么有几个元素就进行几次冒泡，虽然n-1次也行
 */
public class Bubble {

    /*
    对数组a中的元素进行排序
    */
    public static void sort(Comparable[] a){
        int n = a.length - 1;
        for (int i = n; i > 0; i--){
            for (int j = 0; j < i; j++){
                boolean greater = greater(a[j], a[j + 1]);
                if (greater == true){
                    exch(a, j, j+1);
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
class Test {
    public static void main(String[] args) {
        Integer[] a = {4, 5, 6, 3, 2, 1};
        Bubble.sort(a);
        System.out.println(Arrays.toString(a));
    }
}

