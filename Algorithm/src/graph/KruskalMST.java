package graph;

import linear.Queue;
import priority.MinPriorityQueue;
import uf.UF_Tree_Weighted;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KruskalMST {

    //保存最小生成树的所有边
    private Queue<Edge> mst;
    //索引代表顶点，使用uf.connect(v,w)可以判断顶点v和顶点w是否在同一颗树中，使用uf.union(v,w)可以把顶点v所在的树和顶点w所在的树合并
    private UF_Tree_Weighted uf;
    //存储图中所有的边，使用最小优先队列，对边按照权重进行排序
    private MinPriorityQueue<Edge> pq;

    //根据一副加权无向图，创建最小生成树计算对象
    public KruskalMST(EdgeWeightedGraph G) {
        //初始化mst队列
        this.mst = new Queue<Edge>();
        //初始化并查集对象uf,容量和图的顶点数相同
        this.uf = new UF_Tree_Weighted(G.V());
        //初始化最小优先队列pq，容量比图的边的数量大1，并把图中所有的边放入pq中
        this.pq = new MinPriorityQueue<>(G.E()+1);
        //把图中的所有边存储到pq中
        for (Edge edge : G.edges()) {
            pq.insert(edge);
        }

        //如果优先队列pq不为空，也就是还有边未处理，并且mst中的边还不到V-1条，继续遍历
        while (!pq.isEmpty() && mst.size() < G.V()-1){
            //取出pq中权重最小的边e
            Edge e = pq.delMin();
            //获取边e的两个顶点v和w
            int v = e.either();
            int w = e.other(v);
            /*
            通过uf.connect(v,w)判断v和w是否已经连通，
            如果连通:则证明这两个顶点在同一棵树中，那么就不能再把这条边添加到最小生成树中，因为在一棵树的任意两个顶点上添加一条边，都会形成环，而最小生成树不能有环的存在;
            如果不连通:则通过uf.connect(v,w)把顶点v所在的树和顶点w所在的树合并成一棵树,并把这条边加入到mst队列中
            */
            if (uf.connected(v,w)){
                continue;
            }

            uf.union(v, w);
            mst.enqueue(e);
        }
    }
    //获取最小生成树的所有边
    public Queue<Edge> edges() {
        return mst;
    }

}


class TestKruskalMST{
    public static void main(String[] args) throws IOException {
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

        //构建KruskalMST对象，构建出这个对象时，在它的构造方法中就已经调用相关方法，给我们把构成最小生成树的边，权重等都计算好了
        KruskalMST mst = new KruskalMST(G);
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