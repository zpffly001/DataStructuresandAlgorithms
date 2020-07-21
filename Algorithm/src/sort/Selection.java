package sort;

import java.util.Arrays;

public class Selection {

    /*
    对数组a中的元素进行排序
    */
    public static void sort(Comparable[] a) {
        //因为本来a.length-1就和数组元素个数相等，因为索引从0-a.length-1
        //又因为最小索引此时只需要到倒数第二个值就行了，因此索引a.length-2对应的就是数组中，倒数第二个值
        //即需要遍历到倒数第二个值
        for (int i=0;i<=a.length-2;i++){
            int minIndex = i;
            for (int j=i+1;j<=a.length-1;j++){
                if (greater(a[minIndex], a[j])){
                    minIndex = j;//如果大于a[j]就获取它的地址，经过这轮循环后，就找到了，这轮循环中的最小值的索引
                }
            }
            exch(a, minIndex, i);//把最小值索引对应的值，和a[i]交换
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
class TestSelection {
    public static void main(String[] args) {
        Integer[] a = {4, 5, 6, 3, 2, 1};
        Selection.sort(a);
        System.out.println(Arrays.toString(a));
    }
}

