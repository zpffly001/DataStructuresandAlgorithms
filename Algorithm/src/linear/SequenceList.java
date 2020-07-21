package linear;

import java.util.Iterator;

public class SequenceList<T> implements Iterable<T>{

    //存储元素的个数
    private T[] eles;
    //记录当前顺序表中的元素个数
    private int N;

    //构造方法
    public SequenceList(int capacity){
        this.eles = (T[]) new Object[capacity];
        this.N = 0;
    }
    //将一个线性表置为空表
    public void clear(){
        N=0;
    }
    //判断当前线性表是否为空表
    public boolean isEmpty(){
        return N==0;
    }
    //获取线性表的长度
    public int length(){
        return N;
    }
    //获取指定位置的元素
    public T get(int i){
        if (i<0 || i>=N){
            throw new RuntimeException("当前元素不存在！");
        }
        return eles[i];
    }
    //向线型表中添加元素t
    public void insert(T t){

        //元素已经放满了数组，需要扩容,在插入时，扩容判断要在插入之前
        if (N == eles.length){
            resize(eles.length*2);
        }

        eles[N++] = t;
    }
    //在i元素处插入元素t
    public void insert(int i,T t){

        if (i<0 || i>N){
            throw new RuntimeException("插入的位置不合法");
        }

        //元素已经放满了数组，需要扩容,在插入时，扩容判断要在插入之前
        if (N == eles.length){
            resize(eles.length*2);
        }

        //把i位置空出来，i位置及其后面的元素依次向后移动一位
        for (int index=N;index>i;index--){
            eles[index] = eles[index-1];
        }

        //把t放到i位置处
        eles[i] = t;
        //元素数量+1
        N++;
    }
    //删除指定位置i处的元素，并返回该元素
    public T remove(int i){
        if (i<0 || i>N-1){
            throw new RuntimeException("当前要删除的元素不存在");
        }
        //记录i位置处的元素
        T result = eles[i];

        //把i位置后面的元素都向前移动一位
        for (int index=i;index<N-1;index++){
            eles[index] = eles[index+1];
        }

        //当前元素数量-1
        N--;

        //在缩小容量时，要在删除元素后再判断
        if (N>0&&N<eles.length/4){
            resize(eles.length/2);
        }
        return result;
    }
    //查找t元素第一次出现的位置
    public int indexOf(T t){
        if(t==null){
            throw new RuntimeException("查找的元素不合法");
        }

        for (int i=0;i<N;i++){
            if (eles[i] == t){
                return i;
            }
        }
        return -1;
    }


    /**
     * 使该顺序表可以被遍历
     * 1，需要实现Tterable<T>接口，从而实现iterator()，返回一个Iterator<T>迭代器接口对象
     * 2，要想返回一个Iterator<T>迭代器接口对象，我们需要写一个内部类来实现Iterator<T>接口
     * @return
     */
    @Override
    public Iterator<T> iterator() {
        return new SIterator();
    }
    //写一个内部类来实现Iterator<T>接口
    private class SIterator implements Iterator<T>{
        //提供一个指针，来遍历eles[],默认值为0
        private int cur;
        public SIterator(){
            this.cur=0;
        }
        //判断是否还有下一个元素
        @Override
        public boolean hasNext() {
            return cur<N;
        }
        //取出对应指针的元素，并让指针后移
        @Override
        public T next() {
            return eles[cur++];
        }
    }

    //改变容量
    private void resize(int newSize){
        //记录旧数组,先自定义一个数组指向原来的数组
        T[] temp = eles;
        //创建新数组，在堆中重新开辟一份空间，虽然eles不指向原来的数组了，但是还有temp指向原来的数组，因此不会被gc
        eles = (T[]) new java.lang.Object[newSize];
        //把旧数组中的元素拷贝到新数组
        for (int i = 0; i < N; i++) {
            eles[i] = temp[i];
        }

    }
}

//测试代码
class TestSequenceList {
    public static void main(String[] args) {
        //创建顺序表对象
        SequenceList<String> sl = new SequenceList<>(2);
        //测试插入
        sl.insert("姚明");
        sl.insert("科比");
        sl.insert("麦迪");
        sl.insert(1,"詹姆斯");

        for (String s : sl) {
            System.out.println(s);
        }
        System.out.println("-------------------------------------------------------------");
        //测试获取
        String getResult = sl.get(1);
        System.out.println("获取索引1处的结果为："+getResult);
        //测试删除
        String removeResult = sl.remove(0);
        System.out.println("删除的元素是："+removeResult);
        //测试清空
        sl.clear();
        System.out.println("清空后的线性表中的元素个数为:"+sl.length());
    }
}

