package graph;

import linear.Queue;

/**
 * 无向图的实现
 */
public class Graph {
    //顶点数目
    private final int V;
    //边的数目
    private int E;
    //邻接表(只是数组组成的邻接表，把每个数组索引看作是每个节点，数组索引对应的值存储该节点相邻的节点组成的队列的地址)
    private Queue<Integer>[] adj;

    public Graph(int V){
        //初始化顶点数量
        this.V = V;
        //初始化边的数量
        this.E = 0;
        //初始化邻接表
        this.adj = new Queue[V];//初始化容量大小，有几个顶点，就初始化几个
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Queue<Integer>();//对每个顶点后面添加一个队列，用于存储和这个顶点相邻的顶点
        }
    }
    //获取顶点数目
    public int V(){
        return V;
    }
    //获取边的数目
    public int E(){
        return E;
    }
    //向图中添加一条边 v-w，即给v和w这两个顶点添加一条边
    public void addEdge(int v, int w) {
        //把w添加到v的链表中，这样顶点v就多了一个相邻点w
        adj[v].enqueue(w);//每个adj[v]都是一条链表
        //把v添加到w的链表中，这样顶点w就多了一个相邻点v
        adj[w].enqueue(v);
        //边的数目自增1
        E++;
    }
    //获取和顶点v相邻的所有顶点
    public Queue<Integer> adj(int v){
        return adj[v];
    }
}
