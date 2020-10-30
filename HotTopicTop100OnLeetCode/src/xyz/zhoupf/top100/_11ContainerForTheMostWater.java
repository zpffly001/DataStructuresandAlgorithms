package xyz.zhoupf.top100;

/**
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *说明：你不能倾斜容器，且 n 的值至少为 2。
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *示例：输入：[1,8,6,2,5,4,8,3,7] 输出：49
 */
public class _11ContainerForTheMostWater {

    //方法一，双指针。把两条形的x坐标轴之差作为宽，把两条形的低的条形作为高，宽*高的面积可看作盛水的多少
    public int maxArea(int[] height) {
        //i代表x轴右边界，j代表x轴左边界，res记录面积的最大值
        int i = 0, j = height.length - 1, res = 0;
        while (i < j){
            res = height[i] < height[j] ?
                    Math.max(res, (j - i) * height[i++]):
                    Math.max(res, (j - i) * height[j--]);
        }
        return res;
    }

    //方法二：也是双指针，比上面简单易懂
    public int maxArea01(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            }
            else {
                --r;
            }
        }
        return ans;
    }

}
