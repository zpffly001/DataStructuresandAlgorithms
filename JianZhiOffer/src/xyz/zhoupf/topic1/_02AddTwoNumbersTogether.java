package xyz.zhoupf.topic1;

/**
 * 剑指 Offer 04. 二维数组中的查找
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * 示例: 现有矩阵 matrix 如下：
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。给定 target = 20，返回 false。
 */
public class _02AddTwoNumbersTogether {
    //方法零：暴力遍历，遍历此二维数组，如果有相等的返回true，默认返回false，略

    //方法一：线性查找，每次排除一行或一列，利用此矩阵的特性：矩阵中横向从小到大递增，矩阵中横向从上到下递增
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
       int i = matrix.length - 1, j = 0;
       while (i >= 0 && j < matrix[0].length){
           //此时表明此处值大于目标值，因为矩阵中横向从小到大递增，且matrix[i][j]是最下方兼最左方的值，因此，matrix[i][j]右边的值也一定大于目标值target(递增)，因此此行可以pass掉了。即i--；
           if (matrix[i][j] > target) i--;
           //此时表明此处值小于目标值，因为矩阵中横向从上到下递增，且matrix[i][j]是最下方兼最左方的值，因此，matrix[i][j]上边的值也一定小于目标值target(递增)，因此此列可以pass掉了。即j++；
           else if (matrix[i][j] < target) j++;
           //否则就是正确的值
           else return true;
       }
      return false;
    }

    public static void main(String[] args) {

    }

}
