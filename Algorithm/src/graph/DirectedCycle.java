package graph;

/**
 * 基于深度优先搜索，检测有向环的实现
 * 即虽然有的节点可能会被检测多次，但是并不能证明这就是一个有向环，比如0->3  1->3
 * 但是如果这个节点被检测了两次，而这个节点也在辅助栈中，则一定是有向环
 */
public class DirectedCycle {
    //索引代表顶点，值表示当前顶点是否已经被搜索
    private boolean[] marked;
    //记录图中是否有环
    private boolean hasCycle;
    //索引代表顶点，使用栈的思想，记录当前顶点有没有已经处于正在搜索的有向路径上
    private boolean[] onStack;

    //创建一个检测环对象，检测图G中是否有环
    public DirectedCycle(Digraph G){
        //创建一个和图的顶点数一样大小的marked数组
        marked = new boolean[G.V()];
        //创建一个和图的顶点数一样大小的onStack数组
        onStack = new boolean[G.V()];
        //默认没有环
        this.hasCycle = false;
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
        marked[v] = true;
        //让当前顶点进栈
        onStack[v] = true;
        //遍历v顶点的邻接表，得到每一个顶点w(w就是V的邻接表存储的数据的其中一个，该邻接表存储的是所有从v顶点出发直接指向的顶点
        for (Integer w : G.adj(v)) {
            //如果当前顶点w没有被搜索过，则递归搜索与w顶点相通的其他顶点
            if (!marked[w]){
                dfs(G, w);
            }

            //如果顶点w已经被搜索过(如果不在栈中说明是两个不连接的图)，则查看顶点w是否在栈中，如果在，则证明图中有环，修改hasCycle标记，结束循环
            if (onStack[w]){
                hasCycle = true;
                return;
            }
        }
        //当前顶点已经搜索完毕，让当前顶点出栈
        onStack[v]=false;
    }

    //判断是否有环
    public boolean hasCycle(){
        return hasCycle;
    }

}
