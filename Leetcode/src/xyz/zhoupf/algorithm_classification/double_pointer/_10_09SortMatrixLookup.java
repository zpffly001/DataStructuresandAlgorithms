package xyz.zhoupf.algorithm_classification.double_pointer;

/**
 * 10.09-Sort matrix lookup-程序员面试金典
 *给定M×N矩阵，每一行、每一列都按升序排列，请编写代码找出某元素。
 *
 * 示例:  现有矩阵 matrix 如下：
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。   给定 target = 20，返回 false。
 */
public class _10_09SortMatrixLookup {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        if (target < matrix[0][0] || target > matrix[matrix.length - 1][matrix[0].length - 1]) return false;

        int x = 0, y = matrix[0].length - 1;
        while (x >= 0 && x <= matrix.length - 1 && y >= 0 && y <= matrix[0].length - 1){
            if (matrix[x][y] == target) return true;
            if (target < matrix[x][y]) y--;
            else x++;
        }
        return false;
    }

}
