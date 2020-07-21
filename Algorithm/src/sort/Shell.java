package sort;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class Shell {

    /*
    对数组a中的元素进行排序
    */
    public static void sort(Comparable[] a) {
        //1，根据数组a的长度，确定增长量h的初始值
        int h = 1;
        while (h<a.length/2){
            h = 2*h+1;
        }
        //2，希尔排序
        while (h>=1){
            //从索引h开始到最后一个元素的索引，都是要排序的元素
            for (int i=h;i<a.length;i++){
                //这部相当于插入排序
                for (int j=i;j>=h;j-=h){//从未排序的第一个元素开始，倒序遍历以排序的数组，因为希尔排序根据h把数组分为好几组，所以每组进行倒序插入的时候都要以h为步长
                    if (greater(a[j-h], a[j])){
                        exch(a, j-h, j);
                    }else {
                        break;
                    }
                }
            }
            //减小h的值
            h = h/2;
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
class TestShell {
    public static void main(String[] args) {
        Integer[] a = {4, 5, 6, 3, 2, 1};
        Shell.sort(a);
        System.out.println(Arrays.toString(a));
    }
}