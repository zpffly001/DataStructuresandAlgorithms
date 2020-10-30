package xyz.zhoupf.primary_algorithm.chuji01_shuzu;

/**
 * 旋转图像
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 将图像顺时针旋转 90 度。
 * 说明：
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 */
public class chuji_shuzu11 {

//    public void rotate(int[][] matrix) {
//        int length = matrix.length;
//        int[][] v = new int[3][3];
//        int y = 0;
//        for (int k = length; k>=0; k--){
//            int[] b = matrix[k];
//            for (int n = 0; n < b.length; n++){
//                v[n][y] = b[n];
//            }
//            ++y;
//        }
//        matrix = v;
//    }

    public void rotate(int[][] matrix) {
         int length = matrix.length;
         for (int i = 0; i < length / 2; i++) {
             for (int j = i; j < length - 1 - i; j++) {
                 int temp = matrix[i][j];
                 matrix[i][j] = matrix[length - 1 - j][i];
                 matrix[length - 1 - j][i] = matrix[length - 1 - i][length - 1 - j];
                 matrix[length - 1 - i][length - 1 - j] = matrix[j][length - 1 - i];
                 matrix[j][length - 1 - i] = temp;
             }
         }
    }

    public static void main(String[] args) {

    }

}
