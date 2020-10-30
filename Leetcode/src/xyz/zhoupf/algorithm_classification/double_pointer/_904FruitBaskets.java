package xyz.zhoupf.algorithm_classification.double_pointer;


import java.util.HashMap;

/**
 * 904. 水果成篮
 * 在一排树中，第 i 棵树产生 tree[i] 型的水果。
 * 你可以从你选择的任何树开始，然后重复执行以下步骤：
 *
 * 把这棵树上的水果放进你的篮子里。如果你做不到，就停下来。
 * 移动到当前树右侧的下一棵树。如果右边没有树，就停下来。
 * 请注意，在选择一颗树后，你没有任何选择：你必须执行步骤 1，然后执行步骤 2，然后返回步骤 1，然后执行步骤 2，依此类推，直至停止。
 * 你有两个篮子，每个篮子可以携带任何数量的水果，但你希望每个篮子只携带一种类型的水果。
 * 用这个程序你能收集的水果树的最大总量是多少？
 * 示例 1：输入：[1,2,1]   输出：3    解释：我们可以收集 [1,2,1]。
 * 示例 2：输入：[0,1,2,2]    输出：3    解释：我们可以收集 [1,2,2]
 * 如果我们从第一棵树开始，我们将只能收集到 [0, 1]。
 */
public class _904FruitBaskets {

    /**
     * 滑动窗口
     * 问题等价于，找到最长的子序列，最多含有两种“类型”（tree[i] 的值）。
     * 不单独考虑每个元素，转而考虑相同类型的相连块。
     * 比如说，tree = [1, 1, 1, 1, 2, 2, 3, 3, 3] 可以看成是 blocks = [(1, weight = 4), (2, weight = 2), (3, weight = 3)]。
     */
    public int totalFruit(int[] tree) {
        int ans = 0, i = 0;
        Counter count = new Counter();
        for (int j = 0; j < tree.length; j++) {
            count.add(tree[j], 1);
            //此时，说明遇到了第三种水果，不能装了，需要停止。
            //ans记录从某索引开始到结束所盛放最多的水果个数，即先从0开始到条件结束，记录水果个数ans,然后再从下一个/或两个索引开始到停止，直到循环遍历完所有结果。
            while (count.size() >= 3){
                //让i从0开始每次递增，然后把tree[i]键的值每次-1(其实这时map中只有三个键，从头开始减，那么第一个count.get(tree[i]) == 0即值为0的键，表示这个键代表的数小，因此把tree[i]这个键值对从map中移除)
                count.add(tree[i], -1);
                if (count.get(tree[i]) == 0)
                    count.remove(tree[i]);
                i++;
            }
            //每次循环结束，其中果子的数为j - i + 1.
            ans = Math.max(ans, j - i + 1);
        }
        return ans;
    }
    class Counter extends HashMap<Integer, Integer> {
        public int get(int k){
            //判断hashmap中是否有键位k的键值对，如果有则返回其对应的值，否则返回0
            return containsKey(k) ? super.get(k) : 0;
        }
        public void add(int k, int v){
            //向map中添加值，如果已经有对应的键了，那么其对应的值+1
            put(k, get(k) + v);
        }
    }

}
