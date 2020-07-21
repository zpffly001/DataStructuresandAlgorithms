package graph;

import linear.Queue;

/**
 * 加权无向图的实现
 */
public class EdgeWeightedGraph {

    //顶点总数
    private final int V;
    //边的总数
    private int E;
    //邻接表
    private Queue<Edge>[] adj;

    //创建一个含有V个顶点的空加权无向图
    public EdgeWeightedGraph(int V) {
        //初始化顶点数量
        this.V = V;
        //初始化边的数量
        this.E = 0;
        //初始化邻接表
        adj = new Queue[V];
        //初始化邻接表中的空队列
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new Queue<Edge>();
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
    //向加权无向图中添加一条边e
    public void addEdge(Edge e) {
        int v = e.either();//获取加权无向图边的一个顶点
        int w = e.other(v);//获取加权无向图边的另一个顶点

        //因为是无向图，所以边e需要同时出现在两个顶点的邻接表中
        adj[v].enqueue(e);
        adj[w].enqueue(e);
        //边的数量+1
        E++;
    }
    //获取和顶点v关联的所有边
    public Queue<Edge> adj(int v) {
        return adj[v];
    }
    //获取加权无向图的所有边
    public Queue<Edge> edges() {
        //创建一个队列，存储所有的边
        Queue<Edge> allEdge = new Queue<>();
        //遍历顶点，拿到每个顶点的邻接表
        for (int i = 0; i < V; i++) {
            //遍历邻接表，拿到邻接表中的每条边
            for (Edge e : adj[i]) {
                /*
                因为无向图中，每条边对象Edge都会在两个顶点的邻接表中各出现一次，为了不重复获取，暂定一条规则：
                除了当前顶点v，再获取边e中的另外一个顶点w，如果v<w则添加，这样可以保证同一条边只会被统计一次
                */
                if (i < e.other(i)){
                    allEdge.enqueue(e);
                }
            }
        }
        return allEdge;
    }



}
