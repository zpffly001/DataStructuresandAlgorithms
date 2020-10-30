package xyz.zhoupf.topic1;

/**
 * 13，矩阵中的路径
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一格开始，每一步可以在矩阵中向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。例如，在下面的3×4的矩阵中包含一条字符串“bfce”的路径（路径中的字母用加粗标出）。
 * [["a","b","c","e"],
 * ["s","f","c","s"],
 * ["a","d","e","e"]]
 * 但矩阵中不包含字符串“abfb”的路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入这个格子。
 * 示例 1：输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"输出：true
 * 示例 2：输入：board = [["a","b"],["c","d"]], word = "abcd"  输出：false
 */
public class _012PathInMatrix {

    public static boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }
    private static boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        //分别代表i跑出上限，下限；j跑出上限，下限和当前的值不等于目标值时，返回false。
        if (i >= board.length || i < 0 || j >= board[i].length || j < 0 || board[i][j] != word[k]) return false;
        //k代表当前目标字符在 word 中的索引，(k == word.length - 1)此时即最后一个了，即匹配完了
        if (k == word.length - 1) return true;
        //执行到这里，就表明board[i][j]和word[k]相等，因此下面在递归时，k+1
        char tmp = board[i][j];
        board[i][j] = '/';
        //即递归遍历当前节点的下，上，左，右，四个方向，判断四个相邻字符是否右和目标字符相等的，且k+1；
        boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) || dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i , j - 1, k + 1);
        board[i][j] = tmp;
        return res;
    }

    /**
     * 当然要恢复的呀，将 board[i][j] 标记为 '\0' 只是假设它一定是路径上的一点，然后再从它的上下左右去寻找剩下的。但如果它的上下左右递归下去并不能完美匹配出对应word的所有元素的路径，就说明 board[i][j] 并不一定是路径上的一点，我们就去下一个点，假设下一个点一定是路径上的一点，如此往复。所以一旦确定 board[i][j] 并不一定是路径上的点之后，就要给它恢复才行
     */

    public static void main(String[] args) {
        char[][] board = {{'a','b','c','e'}, {'s','f','c','s'}, {'a','d','e','e'}};
        String word = "abcced";
        System.out.println(exist(board, word));
    }

}
