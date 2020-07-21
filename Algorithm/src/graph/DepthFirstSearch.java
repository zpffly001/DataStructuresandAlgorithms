package graph;

/**
 * 深度优先搜索
 */
public class DepthFirstSearch {
    //该数组的索引代表顶点，数组索引对应的数组的值表示当前顶点是否已经被搜索
    private boolean[] marked;
    //记录有多少个顶点与s顶点相通
    private int count;

    //构造深度优先搜索对象，使用深度优先搜索找出G图中s顶点的所有相邻顶点
    public DepthFirstSearch(Graph G,int s){
        //创建一个和图的顶点数一样大小的布尔数组，正好可以把全部索引，看作图的全部顶点，然后该布尔数组索引对应的值true/false表示该顶点是否被搜索
        marked = new boolean[G.V()];
        //搜索G图中与顶点s相通的所有顶点
        dfs(G, s);
    }

    //使用深度优先搜索找出G图中v顶点的所有相邻顶点
    private void dfs(Graph G, int v){
        //把当前顶点标记为已搜索
        marked[v]=true;
        //遍历v顶点的邻接表，得到每一个顶点w
        for (Integer w : G.adj(v)) {//G.adj(v)方法返回的是一个Queue队列，而该队列已经提供了这种遍历方式
            //如果当前顶点w没有被搜索过，则递归搜索与w顶点相通的其他顶点
            if (!marked[w]){
                dfs(G, w);
            }
        }
        //相通的顶点数量+1
        count++;
    }

    //判断w顶点与s顶点是否相通
    public boolean marked(int w){
        return marked[w];
    }

    //获取与顶点s相通的所有顶点的总数
    public int count(){
        return count;
    }
}

class TestDepthFirstSearch{
    public static void main(String[] args) {
        //准备Grap图h对象
        Graph G = new Graph(13);
        G.addEdge(0, 5);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(0, 6);
        G.addEdge(5, 3);
        G.addEdge(5, 4);
        G.addEdge(4, 6);
        G.addEdge(3, 4);
        G.addEdge(7, 8);

        G.addEdge(9, 11);
        G.addEdge(9, 10);
        G.addEdge(9, 12);
        G.addEdge(11, 12);

        //把图对象，和起点传入。构建深度优先搜索对象
        DepthFirstSearch search = new DepthFirstSearch(G, 0);

        //测试与某个顶点相通的顶点数量
        int count = search.count();
        System.out.println("与起点0相通的顶点数量" + count);

        //测试摸个顶点与起点是否相同
        boolean marked1 = search.marked(5);
        System.out.println("顶点5与起点0是否相通" + marked1);
        boolean marked2 = search.marked(7);
        System.out.println("顶点7与起点0是否相通" + marked2);
    }
}