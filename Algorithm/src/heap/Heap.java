package heap;

/**
 * 前几个和树相关的数据结构都是由链表构成的
 * 而这次的堆是使用数组构成的，而且从索引1开始。且1处存储的值就是根节点，也是最大的值
 * 且堆是一个完全二叉树
 * 例：二叉查找树，就是在某段树中，左子树小于当前根节点，右子树大于当前根节点
 * @param <T>
 */
public class Heap<T extends Comparable<T>> {

    //存储堆中的元素
    private T[] items;
    //记录堆中元素的个数
    private int N;

    public Heap(int capacity){
        //因为使用泛型存储T类型数据，而T类型又继承了Comparable，因此多态性可以看出T类型，可以看作Comparable接口类型
        //items = (T[]) new Comparable[capacity+1];
        this.items = (T[]) new Comparable[capacity+1];
        this.N = 0;
    }

    //判断堆中索引i处的元素是否小于索引j处的元素
    private boolean less(int i,int j){
        return items[i].compareTo(items[j]) < 0;
    }

    //交换堆中i索引和j索引处的值
    private void exch(int i,int j){
        T tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }

    //往堆中插入一个元素
    public void insert(T t){
        items[++N] = t;//先进行++操作，从而把下标为0的索引废弃掉
        swim(N);//使新插入的元素上浮到合适的位置
    }

    //使用上浮算法，使索引k处的元素能在堆中处于一个正确的位置
    //就是让新插入的节点和他的父节点进行比较，如果大于父节点，则和父节点互换位置，直到合适为止，这就是上浮
    //且在堆中若新插入节点的数组索引为k，则其父节点索引为k/2，即item[k]与item[k/2]比较
    //左子节点索引为偶数其父节点索引为k/2，而右节点为基数，要(k-1)/2，而Java中小数点直接就舍去了，因此基数也可以不用减一
    private void swim(int k) {
        while (k > 1){//即索引比到2，2/2=1，即比较到跟节点就行了，因为根节点存储的值最大
            if (less(k/2, k)){
                exch(k/2, k);//父节点小于子节点则交换位置
            }
            k = k/2;//索引取到夫节点位置，在继续向上比较
        }
    }

    //删除堆中最大的元素,并返回这个最大元素
    public T delMax(){
        //创建临时变量存储根节点，即最大值
        T max = items[1];
        //把根节点和数组的最后一个节点进行交换，即完全二叉树中最底层的最右边的叶子节点和根节点交换
        exch(1,N);
        //删除最后位置上的元素，即把根节点删除(因为根节点和数组最后一个元素交换了位置，数组最后一个元素成为了临时根节点)
        items[N] = null;
        //元素个数-1
        N--;
        //因为数组最后一个元素成为了临时根节点，要调用下沉算法，找出剩余所有节点的最大值，使其成为根节点
        sink(1);
        //因为临时变量max指向索引为1的下标，即可以把下沉后的正确的根节点返回
        return max;
    }

    //使用下沉算法，使索引k处的元素能在堆中处于一个正确的位置
    private void sink(int k){
        //如果当前已经是最底层了，就不需要循环了
        //因为一个完全二叉树的左子节点在数组中索引要小，因此比较2k和数组索引n的大小就行了
        while (2*k <= N){
            //找到子结点中的较大者
            int max;//记录较大者的下标
            if (2*k+1 <= N){//存在右子结点
                if (less(2*k, 2*k+1)){
                    max = 2*k+1;
                }else {
                    max = 2*k;
                }
            }else{//不存在右子结点
            max = 2*k;
            }

            //比较当前结点和子结点中的较大者，如果当前结点不小，则结束循环
            if (!less(k, max)){
                break;
            }

            //当前结点小，则交换，
            exch(k,max);
            k = max;//如果该节点还需要继续下沉，则更新k的值
        }
    }

}

//测试代码
class TestHeap {
    public static void main(String[] args) throws Exception {
        Heap<String> heap = new Heap<String>(20);
        heap.insert("G");
        heap.insert("F");
        heap.insert("E");
        heap.insert("C");
        heap.insert("B");
        heap.insert("A");
        heap.insert("D");
        String del;
        while((del=heap.delMax())!=null){
            System.out.print(del+",");
        }
    }
}
