package priority;

/**
 * 优先队列使用的数据结构是堆
 */
public class MaxPriorityQueue<T extends Comparable<T>> {

    //存储堆中的元素
    private T[] items;
    //记录堆中元素的个数
    private int N;

    public MaxPriorityQueue(int capacity) {
        items = (T[]) new Comparable[capacity+1];
        N = 0;
    }
    //获取队列中元素的个数
    public int size() {
        return N;
    }
    //判断队列是否为空
    public boolean isEmpty() {
        return N == 0;
    }
    //判断堆中索引i处的元素是否小于索引j处的元素
    public boolean less(int i, int j) {
        return items[i].compareTo(items[j])<0;
    }
    //交换堆中i索引和j索引处的值
    private void exch(int i, int j) {
        T tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }
    //往堆中插入一个元素
    public void insert(T t) {
        //插入数据，先直接插入到堆数组末尾
        items[++N] = t;//先进行++操作，从而把下标为0的索引废弃掉
        //使新插入的元素上浮到合适的位置
        swim(N);
    }
    //使用上浮算法，使索引k处的元素能在堆中处于一个正确的位置
    private void swim(int k) {
        //如果已经到了根结点，就不需要循环了,这里k最小为2，因为如果小于2，就不会再有父节点的堆数组中的索引
        while (k > 1){
            if (less(k/2, k)){
                exch(k/2, k);
            }
            k = k/2;
        }
    }
    //删除堆中最大的元素,并返回这个最大元素
    public T delMax() {
        T max = items[1];
        //System.out.println(max+"前");
        //交换索引1处和索引N处的值
        exch(1,N);
        //删除最后位置上的元素
        items[N] = null;
        //个数-1
        N--;
        //让临时根节点下沉
        sink(1);
        //返回的是下沉后，排完序的堆的根节点
        //max = items[1];
        //System.out.println(max+"后");
        return max;//这个max的值还是sink()方法执行之前的值，虽然max指向items[1]，但是sink(1)下沉后items[1]已经变了，max却没变，应该是没有在此显示的引用的问题
    }
    //使用下沉算法，使索引k处的元素能在堆中处于一个正确的位置
    private void sink(int k) {
        //如果当前已经是最底层了，就不需要循环了,因为2*k<=N小于堆数组最大索引，表明有子节点，反之则在最底层
        while (2*k <= N){
            //找到子结点中的较大者
            int max;//记录左右节点大的那个的坐标
            if (2*k+1 <= N){//存在右子结点
                if (less(2*k, 2*k+1)){
                    max = 2*k+1;
                }else {
                    max = 2* k;
                }
            }else {
                max = 2*k;
            }

            //比较当前结点和子结点中的较大者，如果当前结点不小，则结束循环
            if (!less(k, max)){
                break;
            }

            //当前结点小，则交换
            exch(k, max);
            //如果该节点还需要继续下沉，则更新k的值
            k = max;
        }
    }


}


//测试代码
class TestMaxPriorityQueue {
    public static void main(String[] args) throws Exception {
        String[] arr = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        MaxPriorityQueue<String> maxpq = new MaxPriorityQueue<>(20);
        for (String s : arr) {
            maxpq.insert(s);
        }
        System.out.println(maxpq.size());
        String del;
        while(!maxpq.isEmpty()){
            del = maxpq.delMax();
            System.out.print(del+",");
        }
    }
}