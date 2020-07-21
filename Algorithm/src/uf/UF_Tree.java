package uf;

import java.util.Scanner;

public class UF_Tree {

    //记录结点元素和该元素所的父结点
    private int[] eleAndGroup;
    //记录并查集中数据的分组个数
    private int count;
    //初始化并查集
    public UF_Tree(int N){
        //初始情况下，每个元素都在一个独立的分组中，所以，初始情况下，并查集中的数据默认分为N个组
        this.count=N;
        //初始化数组
        eleAndGroup = new int[N];
        //把eleAndGroup数组的索引看做是每个结点存储的元素，把eleAndGroup数组每个索引处的值看做是该结点的父结点，那么初始化情况下，i索引处存储的值就是i
        for (int i = 0; i < N; i++) {
            eleAndGroup[i]=i;
        }
    }
    //获取当前并查集中的数据有多少个分组
    public int count(){
        return count;
    }
    //判断并查集中元素p和元素q是否在同一分组中(即判断根节点是否相同，因为并查集的每一个分组都可以看成一个树)
    public boolean connected(int p,int q){
        return find(p)==find(q);
    }

    //元素p所在分组的标识符(就是找元素p所在树的根节点，因为根节点没有父元素，因此根节点对应的组标识符还是初始的，组标识符和字节存储的元素的值一样)
    //因为eleAndGroup数组索引还是节点的值，但是索引对应的值却是节点所在树的根节点，因此如果有索引和索引对应存储的值相同的话就是一个树的根节点
    public int find(int p){
        while (true){//比之前UF类中find方法的优化，合并时要查找整个数组，找出相应的元素所在分组的标识，每次合并都需要遍历整个数组，现在都是存储元素所对应的父节点
            //判断当前元素p的父结点eleAndGroup[p]是不是自己，如果是自己则证明已经是根结点了；
            if (p == eleAndGroup[p]){
                return p;
            }
            //如果当前元素p的父结点不是自己，则让p=eleAndGroup[p]，继续找父结点的父结点,直到找到根结点为止；
            p = eleAndGroup[p];
        }
    }

    //把p元素所在分组和q元素所在分组合并
    public void union(int p,int q){
        //找到p元素所在树的根结点
        int pRoot = find(p);
        //找到q元素所在树的根结点
        int qRoot = find(q);

        //如果p和q已经在同一个树中，则无需合并；
        if (pRoot == qRoot){
            return;
        }

        //如果p和q不在同一个分组，则只需要将p元素所在树根结点的父结点设置为q元素的根结点即可；
        eleAndGroup[pRoot] = qRoot;

        //分组数量-1
        count--;
    }

}

class TestUF_Tree{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请录入并查集中元素的个数:");
        int N = sc.nextInt();
        UF_Tree uf_tree = new UF_Tree(N);
        System.out.println("默认情况下"+uf_tree.count()+"个分组");
        while(true){
            System.out.println("请录入您要合并的第一个点:");
            int p = sc.nextInt();
            System.out.println("请录入您要合并的第二个点:");
            int q = sc.nextInt();
            //判断p和q是否在同一个组
            if (uf_tree.connected(p,q)){
                System.out.println("结点："+p+"结点"+q+"已经在同一个组");
                continue;
            }
            uf_tree.union(p,q);
            System.out.println("总共还有"+uf_tree.count()+"个分组");
        }
    }
}
