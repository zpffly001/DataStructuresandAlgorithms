package graph;

import linear.Queue;

/**
 * 加权有向图的实现
 */
public class EdgeWeightedDigraph {

    //顶点总数
    private final int V;
    //边的总数
    private int E;
    //邻接表,因为这是有向图，每个顶点的邻接表中存储的值都表示从该顶点指出的边。且这条边只需要出现在起点的邻接表中就行了（单向）
    private Queue<DirectedEdge>[] adj;

    //创建一个含有V个顶点的空加权有向图
    public EdgeWeightedDigraph(int V) {
        //初始化顶点数量
        this.V = V;
        //初始化边的数量
        this.E = 0;
        //初始化邻接表
        this.adj = new Queue[V];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Queue<DirectedEdge>();
        }
    }
    //获取图中顶点的数量
    public int V() {
        return V;
    }
    //获取图中边的数量
    public int E() {
        return E;
    }
    //向加权有向图中添加一条边e
    public void addEdge(DirectedEdge e) {
        //获取有向边的起点
        int v = e.from();
        //因为是有向图，所以边e只需要出现在起点v的邻接表中
        adj[v].enqueue(e);
        //边的数量+1
        E++;
    }
    //获取由顶点v指出的所有的边
    public Queue<DirectedEdge> adj(int v) {
        return adj[v];
    }
    //获取加权有向图的所有边
    public Queue<DirectedEdge> edges() {
        //创建一个队列，存储所有的边
        Queue<DirectedEdge> allEdge = new Queue<>();
        //遍历顶点，拿到每个顶点的邻接表
        for (int i = 0; i < V; i++) {
            //遍历邻接表，拿到邻接表中的每条边存储到队列中
            for (DirectedEdge edge : adj[i]) {
                allEdge.enqueue(edge);
            }
        }
        return allEdge;
    }

}
