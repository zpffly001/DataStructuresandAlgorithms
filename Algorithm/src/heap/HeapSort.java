package heap;

import java.util.Arrays;

public class HeapSort {

    //判断heap堆中索引i处的元素是否小于索引j处的元素
    private static boolean less(Comparable[] heap, int i, int j) {
        return heap[i].compareTo(heap[j]) < 0;
    }

    //交换heap堆中i索引和j索引处的值
    private static void exch(Comparable[] heap, int i, int j) {
        Comparable tmp = heap[i];
        heap[i] = heap[j];
        heap[j] = tmp;
    }

    //根据原数组source，构造出堆heap
    private static void createHeap(Comparable[] source, Comparable[] heap) {
        //1.把source中的数据拷贝到heap中，从heap的1索引处开始填充
        System.arraycopy(source, 0, heap, 1, source.length);
        //2.从heap索引的一半处开始倒叙遍历，对得到的每一个元素做下沉操作
        //因为索引的一半之后都是叶子节点，因为 2*索引=子节点索引，然后对一半之前的索引依次做下沉操作，使堆有序，而叶子节点根本没有子节点，因此不能下沉，下沉完毕后堆就是有序的了
        for (int i = (heap.length-1)/2; i>0 ; i--) {
            sink(heap,i,heap.length-1);//heap是堆数组，i是下沉节点的索引，heap.length-1是这个索引及其之前的索引可以参与到本次下沉，我们是在整个堆中进行下沉，因此要为堆数组最大的索引heap.length-1(数组中最大索引为数组长度-1)
        }
    }

    //在heap堆中，对target处的元素做下沉，范围是0~range（range初始为堆数组最大的索引heap.length-1(数组中最大索引为数组长度-1)）
    private static void sink(Comparable[] heap, int target, int range){
        //如果当前已经是最底层了，就不需要循环了
        //因为一个完全二叉树的左子节点在数组中索引要小，因此比较2k和数组索引n的大小就行了
        while (2*target <= range){
            //1.找出target结点的两个子结点中的较大值的索引
            int max;
            if (2*target+1 <= range){
                //存在右子结点
                if (less(heap,2*target,2*target+1)){
                    max=2*target+1;
                }else {
                    max=2*target;
                }
            }else {//不存在右子节点
                max=2*target;
            }

            //2.如果当前结点的值小于子结点中的较大值，则交换
            if(less(heap,target,max)){
                exch(heap,target,max);
            }
            //3.更新target的值
            target=max;//如果该节点还需要继续下沉，则更新target的值
        }
    }

    //对source数组中的数据从小到大排序
    public static void sort(Comparable[] source) {
        //1.创建一个比原数组大1的数组，因为我们在堆数组总废弃了0索引，因此如果要存储和之前普通数组一样多的数据的话，堆数组长度就需要再+1
        Comparable[] heap = new Comparable[source.length + 1];
        //2.构造堆
        createHeap(source,heap);
        //3.堆排序
        //3.1定义一个变量，记录heap中未排序的所有元素中最大的索引,初始所有都没排序
        int N = heap.length-1;
        //通过循环，交换1索引处的元素和未排序的元素中的最大索引处的元素
        while (N!=1){//如果N为1，则表明未排序的索引为1，则不需要拍虚了
            //3.2交换heap中索引1处的元素和N处的元素
            exch(heap,1,N);
            N--;//经上面操作后，N索引处的元素已经是有序的了，因此再下沉时不需要再考虑N索引处元素了
            //3.3对索引1处的元素在0~N范围内做下沉操作
            sink(heap,1,N);
        }

        //4.heap中的数据已经有序，拷贝到source中
        System.arraycopy(heap, 1,source, 0, source.length);
    }
}

//测试代码
class Test {
    public static void main(String[] args) throws Exception {
        String[] arr = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        HeapSort.sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
