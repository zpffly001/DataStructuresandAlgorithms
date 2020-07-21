package uf;

import java.util.Scanner;

public class UF {
    //记录结点元素和该元素所在分组的标识
    private int[] eleAndGroup;
    //记录并查集中数据的分组个数
    private int count;
    //初始化并查集，N为初始化节点的个数，每个节点看作是一个分组
    public UF(int N){
        //初始情况下，每个元素都在一个独立的分组中，所以，初始情况下，并查集中的数据默认分为N个组
        this.count = N;
        //初始化数组
        eleAndGroup = new int[N];
        //把eleAndGroup数组的索引看做是每个结点存储的元素，把eleAndGroup数组每个索引处的值看做是该结点所在的分组，那么初始化情况下，i索引处存储的值就是i
        for (int i = 0; i < eleAndGroup.length; i++) {
            eleAndGroup[i] = i;
        }
    }
    //获取当前并查集中的数据有多少个分组
    public int count(){
        return count;
    }
    //元素p所在分组的标识符
    public int find(int p){
        return eleAndGroup[p];
    }
    //判断并查集中元素p和元素q是否在同一分组中
    public boolean connected(int p,int q){
        return find(p)==find(q);
    }
    //把p元素所在分组和q元素所在分组合并
    public void union(int p,int q){
        //如果p和q已经在同一个分组中，则无需合并；
        if (connected(p, q)){
            return;
        }

        //如果p和q不在同一个分组，则只需要将p元素所在组的所有的元素的组标识符修改为q元素所在组的标识符即可
        //找到p所在分组的标识符
        int pGroup = find(p);
        //找到q所在分组的标识符
        int qGroup = find(q);
        //遍历eleAndGroup数组(即并查集中所有的元素都以该数组的索引的形式存在，并查集中所有元素所对应的组标识都以该数组的对应索引的值的形式存在)
        //即遍历并查集，让p所在组的所有元素的组标识符变为q所在分组的组标识符，即完成了合并组。
        for (int i = 0; i < eleAndGroup.length; i++) {
            if (eleAndGroup[i] == pGroup){
                eleAndGroup[i] = qGroup;
            }
        }
        //分组数量-1
        count--;
    }

}


//测试并查集
class TestUF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请录入并查集中元素的个数:");
        int N = sc.nextInt();
        UF uf = new UF(N);
        System.out.println("默认情况下"+uf.count()+"个分组");
        while(true){
            System.out.println("请录入您要合并的第一个点:");
            int p = sc.nextInt();
            System.out.println("请录入您要合并的第二个点:");
            int q = sc.nextInt();
            //判断p和q是否在同一个组
            if (uf.connected(p,q)){
                System.out.println("结点："+p+"结点"+q+"已经在同一个组");
                continue;
            }
            uf.union(p,q);
            System.out.println("总共还有"+uf.count()+"个分组");
        }
    }
}