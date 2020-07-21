package graph;

import linear.Queue;
import priority.IndexMinPriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 最短路径树
 * 最短路径树的组成：图+顶点。
 * 这里是：加权有向图 + 顶点
 */
public class DijkstraSP {
    //索引代表顶点，值表示从顶点s到当前顶点的最短路径上的最后一条边
    //即索引代表顶点，值表示该顶点和上一个顶点之间形成的那条有向加权边
    private DirectedEdge[] edgeTo;
    //索引代表顶点，值从顶点s到当前顶点的最短路径的总权重
    private double[] distTo;
    //存放树中顶点与非树中顶点之间的有效横切边
    private IndexMinPriorityQueue<Double> pq;

    //根据一副加权有向图G和顶点s，创建一个计算顶点为s的最短路径树对象
    public DijkstraSP(EdgeWeightedDigraph G, int s){
        //创建一个和图的顶点数一样大小的DirectedEdge数组，表示边
        this.edgeTo = new DirectedEdge[G.V()];
        //创建一个和图的顶点数一样大小的double数组，表示权重，并且初始化数组中的内容为无穷大，无穷大即表示不存在这样的边
        this.distTo = new double[G.V()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        //创建一个和图的顶点数一样大小的索引优先队列，存储有效横切边
        this.pq = new IndexMinPriorityQueue<>(G.V());
        //默认让顶点s进入树中，但s顶点目前没有与树中其他的顶点相连接，因此初始化distTo[s]=0.0
        distTo[s] = 0.0;
        //使用顶点s和权重0.0初始化pq
        pq.insert(s, 0.0);
        //遍历有效边队列
        while (!pq.isEmpty()){
            //松弛图G中的顶点
            relax(G,pq.delMin());
        }
    }

    //松弛图G中的顶点v,就是放松从该顶点指出的所有的边
    private void relax(EdgeWeightedDigraph G, int v){
        //松弛顶点v就是松弛顶点v邻接表中的每一条边，遍历邻接表
        for (DirectedEdge edge : G.adj(v)) {//DirectedEdge就是顶点v指出的所有的边
            //获取边e的终点w(其实就是顶点v直接指向的那几个顶点)
            int w = edge.to();
            //起点s到顶点w的权重是否大于起点s到顶点v的权重+边e的权重,如果大于，则修改s->w的路径：edgeTo[w]=e,并修改distTo[v] = distTo[v]+e.weitht(),如果不大于，则忽略
            if (distTo(w) > distTo(v)+ edge.weight()){
                distTo[w] = distTo[v] + edge.weight();
                edgeTo[w] = edge;//然后把指向w节点的边更新为当前边

                //如果顶点w已经存在于优先队列pq中，则重置顶点w的权重
                if (pq.contains(w)){
                    pq.changeItem(w, distTo(w));
                }else {
                    //如果顶点w没有出现在优先队列pq中，则把顶点w及其权重加入到pq中
                    pq.insert(w, distTo(w));
                }
            }
        }
    }

    //获取从顶点s到顶点v的最短路径的总权重
    public double distTo(int v){
        return distTo[v];
    }

    //判断从顶点s到顶点v是否可达
    public boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    //查询从起点s到顶点v的最短路径中所有的边
    public Queue<DirectedEdge> pathTo(int v){
        //如果顶点s到v不可达，则返回null
        if (!hasPathTo(v)){
            return null;
        }
        //创建队列Queue保存最短路径上的所有的边
        Queue<DirectedEdge> edges = new Queue<>();
        //从顶点v开始，逆向寻找，一直找到顶点s为止，而起点s为最短路劲树的根结点，所以edgeTo[s]=null;
        while (true){
            //先得到指向顶点v的最短路径上的上一条边(在构造方法中就调用了相应的最短路径方法，因此这里不用调用，使用的属性就是最短路径的属性)
            DirectedEdge e = edgeTo[v];
            if (e==null){//如果最短路径的上一条边为空，则表示找到头了(我们是从v逆序找到根节点，只有根节点没有上一条边)，可以结束了。找到了最短路径上的所有的边
                break;
            }
            edges.enqueue(e);
            //继续找上一个顶点对应的边，直到循环到根节点，找到最短路径上的所有的边为止
            v = e.from();
        }
        return edges;
    }


}

class TestDijkstraSP{
    public static void main(String[] args) throws Exception {
        //创建输入流
        BufferedReader reader = new BufferedReader(new InputStreamReader(TestDijkstraSP.class.getClassLoader().getResourceAsStream("min_route_test.txt")));
        int total = Integer.parseInt(reader.readLine());
        //创建加权有向图，只有顶点，边是空白的
        EdgeWeightedDigraph G = new EdgeWeightedDigraph(total);
        //读取边的数量
        int edgeNumbers = Integer.parseInt(reader.readLine());
        //循环往图中添加边
        for (int i = 0; i < edgeNumbers; i++) {
            String line = reader.readLine();
            int v = Integer.parseInt(line.split(" ")[0]);
            int w = Integer.parseInt(line.split(" ")[1]);
            double weight = Double.parseDouble(line.split(" ")[2]);
            //创建一个有向加权边
            DirectedEdge e = new DirectedEdge(v, w, weight);
            //给加权有向图中添加有向加权边
            G.addEdge(e);
        }
        //根据图G和顶点0，构建DijkstraSP对象
        DijkstraSP dijkstraSP = new DijkstraSP(G, 0);
        //获取起点0到顶点6的最短路径
        Queue<DirectedEdge> edges = dijkstraSP.pathTo(6);
        //打印输出
        for (DirectedEdge edge : edges) {
            System.out.println(edge.from() + "->" + edge.to() + "::" + edge.weight());
        }

    }
}