package graph;

import linear.Stack;

/**
 * 拓扑排序实现
 * 前面已经实现了环的检测以及顶点排序，那么现在进行拓扑排序
 * 先检测有没有环，如果没有环，则调用顶点排序即可
 */
public class TopoLogical {

    //顶点的拓扑排序
    private Stack<Integer> order;

    //构造拓扑排序对象
    public TopoLogical(Digraph G){
        //创建检测环对象，检测有向图图G中是否有环
        DirectedCycle dCycle = new DirectedCycle(G);
        if (!dCycle.hasCycle()){
            //如果有向图G没有环，创建顶点排序对象，进行顶点排序
            DepthFirstOrder depthFirstOrder = new DepthFirstOrder(G);
            //把有向图中的顶点生成线性序列，存储在栈中
            order = depthFirstOrder.reversePost();
        }
    }
    //判断图G是否有环
    private boolean isCycle(){
        return order==null;
    }
    //获取拓扑排序的所有顶点
    public Stack<Integer> order(){
        return order;
    }

}


//测试拓扑排序
class TestTopoLogical{
    public static void main(String[] args) {
        //准备有向图
        Digraph G = new Digraph(6);
        G.addEdge(0, 2);
        G.addEdge(0, 3);
        G.addEdge(2, 4);
        G.addEdge(3, 4);
        G.addEdge(4, 5);
        G.addEdge(1, 3);

        //通过topoLogical对象对有向图中的顶点进行排序
        TopoLogical topoLogical = new TopoLogical(G);

        Stack<Integer> order = topoLogical.order();
        StringBuilder sb = new StringBuilder();
        for (Integer w : order) {
            sb.append(w + "->");
        }
        String str = sb.toString();
        int index = str.lastIndexOf("->");
        //str.substring(0, index);//这样截取后相当于再常量池中有新创建了一个String类型对象，因此要重新引用
        str =  str.substring(0, index);
        System.out.println(str);


    }
}