package xyz.zhoupf.primary_algorithm.chuji01_shuzu;

import java.util.HashMap;

public class chuji_shuzu10 {
    /**
     * 思路一暴力解题法
     * 一个简单的解决方案是遍历该 9 x 9 数独 三 次，以确保：
     * 行中没有重复的数字。
     * 列中没有重复的数字。
     * 3 x 3 子数独内没有重复的数字。
     * 解题过程略
     */


    /**
     * 一次迭代做完所有需要做的事情
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        // init data
        HashMap<Integer, Integer>[] rows = new HashMap[9];
        HashMap<Integer, Integer> [] columns = new HashMap[9];
        HashMap<Integer, Integer> [] boxes = new HashMap[9];
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<Integer, Integer>();
            columns[i] = new HashMap<Integer, Integer>();
            boxes[i] = new HashMap<Integer, Integer>();
        }

        // validate a board
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int n = (int)num;
                    int box_index = (i / 3 ) * 3 + j / 3;

                    // keep the current cell value
                    rows[i].put(n, rows[i].getOrDefault(n, 0) + 1);
                    columns[j].put(n, columns[j].getOrDefault(n, 0) + 1);
                    boxes[box_index].put(n, boxes[box_index].getOrDefault(n, 0) + 1);

                    // check if this value has been already seen before
                    if(rows[i].get(n) > 1 || columns[j].get(n) > 1 || boxes[box_index].get(n) > 1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public static boolean isValidSudoku1(char[][] board) {
        HashMap<Integer, Integer>[] rows = new HashMap[9];//9行
        HashMap<Integer, Integer> [] columns = new HashMap[9];//9列
        HashMap<Integer, Integer> [] boxes = new HashMap[9];//9个3*3的方格
        //给每个HashMap数组的每个元素声明为HashMap
        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<>();
            columns[i] = new HashMap<>();
            boxes[i] = new HashMap<>();
        }

        //两层遍历表示一行一行的遍历9*9方格
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];//遍历9*9方格中的每个字符，并一次赋值给num
                if (num != '.'){
                    int n = (int)num;
                    int box_index = (i / 3)*3 + j / 3;

                    rows[i].put(n, rows[i].getOrDefault(n, 0) + 1);//把当前传入的字符放入Map中，默认数量为1..该字符的数量作为map的值，该字符作为键。如果值大于1，即检查出了该行有重复的值。则该数独无效
                    columns[j].put(n, columns[j].getOrDefault(n, 0) + 1);
                    boxes[box_index].put(n, boxes[box_index].getOrDefault(n, 0) + 1);

                    if(rows[i].get(n) > 1 || columns[j].get(n) > 1 || boxes[box_index].get(n) > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        char[][] b = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'},
        };
        System.out.println(isValidSudoku1(b));
    }

}
