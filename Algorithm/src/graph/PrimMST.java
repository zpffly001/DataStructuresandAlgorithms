package graph;

import linear.Queue;
import priority.IndexMinPriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PrimMST {

    //索引代表顶点，值表示当前顶点和最小生成树之间的最短边
    private Edge[] edgeTo;
    //索引代表顶点，值表示当前顶点和最小生成树之间的最短边的权重
    private double[] disTo;
    //索引代表顶点，如果当前顶点已经在树中，则值为true，否则为false
    private boolean[] marked;
    //存放树中顶点与非树中顶点之间的有效横切边
    private IndexMinPriorityQueue<Double> pq;

    //根据一副加权无向图，创建最小生成树计算对象
    public PrimMST(EdgeWeightedGraph G) {
        //创建一个和图的顶点数一样大小的Edge数组，表示边
        this.edgeTo = new Edge[G.V()];
        //创建一个和图的顶点数一样大小的double数组，表示权重，并且初始化数组中的内容为无穷大，无穷大即表示不存在这样的边
        this.disTo = new double[G.V()];
        for (int i = 0; i < disTo.length; i++) {
            //初始化数组中的每个变量都为一个很大的值，因此在我们后期存入数据的时候，肯定比这个数值小，因此便于我们比较
            disTo[i] = Double.POSITIVE_INFINITY;
        }
        //创建一个和图的顶点数一样大小的boolean数组，表示当前顶点是否已经在树中
        this.marked = new boolean[G.V()];
        //创建一个和图的顶点数一样大小的索引优先队列，存储有效横切边(即和最小生成树所在顶点之间的连线，初始最小生成树只有0顶点)
        pq = new IndexMinPriorityQueue<Double>(G.V());

        //Prim算法，初始让0顶点进入最小生成树中
        //默认让顶点0进入树中，但0顶点目前没有与树中其他的顶点相连接，因此初始化distTo[0]=0.0
        disTo[0] = 0.0;
        //使用顶点0和权重0初始化pq
        pq.insert(0, 0.0);
        //遍历有效边队列
        while (!pq.isEmpty()) {
            //找到权重最小的横切边对应的顶点，加入到最小生成树中
            visit(G, pq.delMin());//pq.delMin()返回的是最小值的索引，即顶点
        }
    }

    //将顶点v添加到最小生成树中，并且更新数据
    private void visit(EdgeWeightedGraph G, int v) {
        //把顶点v添加到树中
        marked[v] = true;
        //遍历顶点v的邻接表,得到每一条边Edge e（即拿到与当前顶点相连的每一条边）
        for (Edge e : G.adj(v)) {
            //边e的一个顶点是v，找到另外一个顶点w；
            int w = e.other(v);
            //检测是否已经在树中，如果在，则继续下一次循环，如果不在，则需要修正当前顶点w距离最小生成树的最小边edgeTo[w]以及它的权重distTo[w]，还有有效横切边也需要修正
            if (marked[w]) {
                continue;
            }

            //如果v-w边e的权重比目前distTo[w]权重小(distTo[w]可能之前的树中某个顶点也与w相连，因此distTo[w]本来就有值，我们在更新前必须比较一下当前新插入到最小生成树的节点v到w的的边e的权重是否小于distTo[w]中存储的值，若小于则更新值)，则需要修正数据
            if (e.weight() < disTo[w]){
                //如果v-w边e的权重比目前distTo[w]权重小，则需要修正数据(之前的数据是之前顶点到w的距离，但不一定是最终的最短距离)
                edgeTo[w] = e;
                //把顶点w距离最小生成树的边的权重修改为e.weight()
                disTo[w] = e.weight();
                //这个最小优先队列存储的是，和当前最小生成树(可能还没有生成完全，只包含一两个节点0,7)中所有节点直接相连的顶点形成的最小的权重(可能4节点既与0节点相连，又与7节点相连，因此更新前要先比较该索引位置存储是否有值，如果没有直接插入，如果有则比较)
                //如果pq中存储的有效横切边已经包含了w顶点，则需要修正最小索引优先队列w索引关联的权重值
                if(pq.contains(w)){
                    pq.changeItem(w, e.weight());
                }else {
                    //如果pq中存储的有效横切边不包含w顶点，则需要向最小索引优先队列中添加v-w和其权重值
                    pq.insert(w, e.weight());
                }
            }
        }
    }

    //获取最小生成树的所有边
    public Queue<Edge> edges() {
        //创建队列
        Queue<Edge> edges = new Queue<>();
        //遍历edgeTo数组，找到每一条边，添加到队列中
        for (int i = 0; i < marked.length; i++) {
            if (edgeTo[i]!=null){
                edges.enqueue(edgeTo[i]);
            }
        }
        return edges;
    }


}

class PrimTest{
    public static void main(String[] args) throws Exception {
        //创建输入流
        BufferedReader reader = new BufferedReader(new InputStreamReader(PrimTest.class.getClassLoader().getResourceAsStream("min_create_tree_test.txt")));
        //读取顶点数目，初始化EdgeWeightedGraph图(我们在创建图时，都是传入顶点数量，生成无边的图)
        int total = Integer.parseInt(reader.readLine());
        //生成带权无向图
        EdgeWeightedGraph G = new EdgeWeightedGraph(total);
        //读取边的数目
        int edgeNumber = Integer.parseInt(reader.readLine());
        //循环读取每一条边，并调用addEdge方法，给带权无向图G生成边
        for (int i = 0; i < edgeNumber; i++) {
            String line = reader.readLine();
            //获取顶点v
            int v = Integer.parseInt(line.split(" ")[0]);
            //获取顶点w
            int w = Integer.parseInt(line.split(" ")[1]);
            //获取这两个顶点组成的边的权重
            double weight = Double.parseDouble(line.split(" ")[2]);
            //生成带有权重的边
            Edge edge = new Edge(v, w, weight);
            //生成边
            G.addEdge(edge);
        }

        //构建PrimMST对象，构建出这个对象时，在它的构造方法中就已经调用相关方法，给我们把构成最小生成树的边，权重等都计算好了
        PrimMST mst = new PrimMST(G);
        Queue<Edge> edges = mst.edges();
        //打印输出
        for (Edge edge : edges) {
            if (edge!=null){
                int v = edge.either();
                int w = edge.other(v);
                double weight = edge.weight();
                System.out.println(v + "-" + w + "::" + weight);
            }
        }
    }
}