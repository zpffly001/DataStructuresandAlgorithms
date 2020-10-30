package xyz.zhoupf.primary_algorithm.chuji09_other;

import java.util.ArrayList;
import java.util.List;

/**
 * 杨辉三角
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */
public class chuji04_yanghuisanjiao {

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        if (numRows == 0) return triangle;

        //因为第一行只有一个数，永远是1
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);

        for (int rowNum  = 1; rowNum  < numRows; rowNum ++) {
           List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = triangle.get(rowNum - 1);

            //每一行的第一个元素(索引为0)一定为1
            row.add(1);
            //给每行的元素赋值，从索引1开始，因为索引0处已经赋值为1.
            for (int j = 1; j < rowNum; j++) {
                //每行的第1-leng-2个元素的值都是上一行对应索引-1，和对应索引这两个值的和
                row.add(prevRow.get(j - 1) + prevRow.get(j));
            }
            //每一行的最后一个元素(索引为length-1)一定为1
            row.add(1);

            triangle.add(row);
        }
        return triangle;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = generate(5);
        for (List<Integer> list : lists) {
            for (Integer num : list) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

}
