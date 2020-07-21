package graph;

import javax.crypto.Cipher;

/**
 *  加权无向图的边的实现，即带有权重的边
 */
public class Edge implements Comparable<Edge>{

    private final int v;//顶点一
    private final int w;//顶点二
    private final  double weight;//当前边的权重

    //通过顶点v和w，以及权重weight值构造一个边对象
    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    //获取边的权重值
    public double weight(){
        return weight;
    }
    //获取边上的一个点
    public int either(){
        return v;
    }
    //获取边上除了顶点vertex外的另外一个顶点
    public int other(int vertex){
        if (vertex == v){
            //如果传入的顶点vertext是v，则返回另外一个顶点w
            return w;
        }else{
            //如果传入的顶点vertext不是v，则返回v即可
            return v;
        }
    }

    @Override
    public int compareTo(Edge that) {
        int cmp;
        if (this.weight() > that.weight()){
            //如果当前边的权重大于参数边that的权重，返回1
            cmp = 1;
        }else  if (this.weight() < that.weight()){
            //如果当前边的权重小于参数边that的权重，返回-1
            cmp = -1;
        }else {
            //如果当前边的权重等于参数边that的权重，返回0
            cmp=0;
        }
        return cmp;
    }
}
