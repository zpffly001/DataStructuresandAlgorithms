package graph;

import linear.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Stack;

public class DepthFirstPaths {
    //索引代表顶点，值表示当前顶点是否已经被搜索
    private boolean[] marked;
    //起点
    private int s;
    //索引代表顶点，值代表从起点s到当前顶点路径上的最后一个顶点
    private int[] edgeTo;

    //构造深度优先搜索对象，使用深度优先搜索找出G图中起点为s的所有路径
    public DepthFirstPaths(Graph G, int s){
        //创建一个和图的顶点数一样大小的布尔数组
        this.marked = new boolean[G.V()];
        //创建一个和图顶点数一样大小的整型数组
        this.edgeTo = new int[G.V()];
        //初始化顶点
        this.s = s;
        //搜索G图中起点为s的所有路径
        dfs(G, s);
    }

    //使用深度优先搜索找出G图中v顶点的所有相邻顶点
    private void dfs(Graph G, int v){
        //把当前顶点标记为已搜索
        marked[v] = true;
        //遍历v顶点的邻接表，得到每一个顶点w
        for (Integer w : G.adj(v)) {
            //如果当前顶点w没有被搜索过，则将edgeTo[w]设置为v,表示w的前一个顶点为v，并递归搜索与w顶点相通的其他顶点
            if (!marked[w]){
                edgeTo[w] = v;
                dfs(G,w);
            }
        }
    }


    //判断w顶点与s顶点是否存在路径(因为初始化时构造函数传入的就是起点s，然后dfs(Graph G, int s)遍历所有和起点联通的节点，如果联通就把联通的节点置为true，因此此次遍历中如果顶点的mark[顶点]=true，则表明该顶点和起点联通)
    public boolean hasPathTo(int v){
        return marked[v];
    }

    //找出从起点s到顶点v的路径(就是该路径经过的顶点)
    public Stack<Integer> pathTo(int v){
        //当前v顶点与s顶点不连通，所以直接返回null，没有路径
        if (!hasPathTo(v)){
            return null;
        }
        //创建路径中经过的顶点的容器/栈
        //Stack<Integer> path = new Stack<>();
        Stack<Integer> path = new Stack<>();
        //第一次把当前顶点v存进去(i=v)，然后将i变换为到达当前顶点的前一个顶点edgeTo[i],在把前一个顶点存进去，继续将x变化为到达前一个顶点的前一个顶点，继续存，一直到i的值为s为止，相当于逆推法，最后把s放进去
        for (int i=v; i!=s; i=edgeTo[i]){
            //把当前顶点放入容器
            path.push(i);
            System.out.println("2");
        }
        //把起点s放入容器，因为我们循环到起点就停止了，并没有把起点入栈
        path.push(s);
        return path;
    }

}

//测试路径查找
class TestDepthFirstPaths{
    public static void main(String[] args) throws IOException {
        //创建输入流
        BufferedReader reader = new BufferedReader(new InputStreamReader(TestDepthFirstPaths.class.getClassLoader().getResourceAsStream("road_find.txt")));

        //读取城市数目，初始化Graph图
        int number = Integer.parseInt(reader.readLine());
        Graph G = new Graph(number);

        //读取城市的连通道路
        int roadNumber = Integer.parseInt(reader.readLine());
        //循环读取道路，并调用addEdge方法，连接这两条道路
        for (int i = 0; i < roadNumber; i++) {
            String line = reader.readLine();
            int p = Integer.parseInt(line.split(" ")[0]);
            int q = Integer.parseInt(line.split(" ")[1]);
            G.addEdge(p, q);
        }
        //根据图G和顶点0路径查找对象
        DepthFirstPaths paths = new DepthFirstPaths(G, 0);
        //调用查找对象的pathTo(4)方法得到路径
        Stack<Integer> path = paths.pathTo(4);
        System.out.println(path);
        //遍历打印
        StringBuilder sb = new StringBuilder();//放到StringBUilder中加了“-”，为了样式好看
        for (Integer w : path) {
            sb.append(w + "-");
        }
        sb.deleteCharAt(sb.length()-1);//去掉最后面一个多余的“-”
        System.out.println(sb);

//        //创建输入流
//        BufferedReader reader = new BufferedReader(new InputStreamReader(TestDepthFirstPaths.class.getClassLoader().getResourceAsStream("road_find.txt")));
//        //读取城市数目，初始化Graph图
//        int number = Integer.parseInt(reader.readLine());
//        Graph G = new Graph(number);
//        //读取城市的连通道路
//        int roadNumber = Integer.parseInt(reader.readLine());
//        //循环读取道路，并调用addEdge方法
//        for (int i = 0; i < roadNumber; i++) {
//            String line = reader.readLine();
//            int p = Integer.parseInt(line.split(" ")[0]);
//            int q = Integer.parseInt(line.split(" ")[1]);
//            G.addEdge(p, q);
//        }
//        //根据图G和顶点0路径查找对象
//        DepthFirstPaths paths = new DepthFirstPaths(G, 0);
//        //调用查找对象的pathTo(4)方法得到路径
//        Stack<Integer> path = paths.pathTo(4);
//        //遍历打印
//        StringBuilder sb = new StringBuilder();
//        for (Integer v : path) {
//            sb.append(v+"-");
//        }
//        sb.deleteCharAt(sb.length()-1);
//        System.out.println(sb);
    }
}