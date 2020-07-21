package priority;

public class MinPriorityQueue<T extends Comparable<T>> {

    //存储堆中的元素
    private T[] items;
    //记录堆中元素的个数
    private int N;

    public MinPriorityQueue(int capacity) {
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
    private boolean less(int i, int j) {
        return items[i].compareTo(items[j]) < 0;
    }
    //交换堆中i索引和j索引处的值
    private void exch(int i, int j) {
        T tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }
    //往堆中插入一个元素
    public void insert(T t) {
        items[++N] = t;
        swim(N);
    }
    //删除堆中最小的元素,并返回这个最小元素
    public T delMin() {
        //索引1处的值是最小值
        T min = items[1];
        //交换索引1处和索引N处的值
        exch(1,N);
        //删除索引N处的值
        items[N] = null;
        //数据元素-1
        N--;
        //对索引1处的值做下沉，使堆重新有序
        sink(1);
        //返回被删除的值
        return min;
    }
    //使用上浮算法，使索引k处的元素能在堆中处于一个正确的位置
    private void swim(int k) {
        //如果已经到了根结点，就不需要循环了,这里k最小为2，因为如果小于2，就不会再有父节点的堆数组中的索引
        while (k > 1){
            if (less(k,k/2)){
                exch(k, k/2);
            }
            k = k/2;
        }
    }
    //使用下沉算法，使索引k处的元素能在堆中处于一个正确的位置
    private void sink(int k) {
        //如果当前已经是最底层了，就不需要循环了,因为2*k<=N小于堆数组最大索引，表明有子节点，反之则在最底层
        while (2 * k <= N) {
            //找出子结点中的较小值的索引
            int min;//记录左右节点小的那个的坐标
            if (2*k+1 <= N){//存在右子结点
                if (less(2*k+1, 2*k)){
                    min = 2*k+1;
                }else {
                    min = 2*k;
                }
            }else {
                min = 2*k;
            }
            //比较当前结点和子结点中的较小者，如果当前结点大，则结束循环
            if (less(k,min)){
                break;
            }
            //当前结点大，交换
            exch(min, k);
            k = min;
        }
    }
}


//测试代码
class TestMinPriorityQueue {
    public static void main(String[] args) throws Exception {
        String[] arr = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
        MinPriorityQueue<String> minpq = new MinPriorityQueue<>(20);
        for (String s : arr) {
            minpq.insert(s);
        }
        System.out.println(minpq.size());
        String del;
        while(!minpq.isEmpty()){
            del = minpq.delMin();
            System.out.print(del+",");
        }
    }
}