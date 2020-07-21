package graph;

import linear.Stack;

/**
 * 基于深度优先的顶点排序
 * 即把图中的顶点生成线性序列，存储在栈中
 */
public class DepthFirstOrder {

    //索引代表顶点，值表示当前顶点是否已经被搜索
    private boolean[] marked;
    //使用栈，存储顶点序列
    private Stack<Integer> reversePost;

    //创建一个顶点排序对象
    public DepthFirstOrder(Digraph G){
        //创建一个和图的顶点数一样大小的marked数组
        marked = new boolean[G.V()];
        reversePost = new Stack<>();
        //遍历搜索图中的每一个顶点
        for (int i = 0; i < G.V(); i++) {
            //如果当前顶点没有搜索过，则搜索
            if (!marked[i]){
                dfs(G, i);
            }
        }
    }

    //基于深度优先搜索，检测图G中是否有环
    private void dfs(Digraph G, int v){
        //把当前顶点标记为已搜索
        marked[v]=true;
        //遍历v顶点的邻接表，得到每一个顶点w
        for (Integer w : G.adj(v)) {
            if (!marked[w]){
                dfs(G, w);
            }
        }
        //当前顶点已经搜索完毕，让当前顶点入栈
        reversePost.push(v);
    }

    //获取顶点线性序列
    public Stack<Integer> reversePost(){
        return reversePost;
    }


}
