import java.util.Random;

//实验一 生命游戏
public class GameOfLife {
    private int rows;
    private int cols;
    private boolean[][] grid;
    private boolean[][] nextGrid;

    public GameOfLife(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new boolean[rows][cols];
        this.nextGrid = new boolean[rows][cols];
        initializeGrid();
    }

    // 随机初始化网格
    private void initializeGrid() {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = random.nextBoolean();
            }
        }
    }

    // 打印当前网格状态
    public void printGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(grid[i][j] ? "O " : ". ");
            }
            System.out.println();
        }
        System.out.println();
    }

    // 更新网格到下一代
    public void updateGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int liveNeighbors = countLiveNeighbors(i, j);
                if (grid[i][j]) {
                    // 规则1和规则3
                    nextGrid[i][j] = liveNeighbors == 2 || liveNeighbors == 3;
                } else {
                    // 规则4
                    nextGrid[i][j] = liveNeighbors == 3;
                }
            }
        }

        // 将nextGrid复制回grid
        for (int i = 0; i < rows; i++) {
            System.arraycopy(nextGrid[i], 0, grid[i], 0, cols);
        }
    }

    // 计算一个细胞的活邻居数量
    private int countLiveNeighbors(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue; // 跳过当前细胞
                int newRow = row + i;
                int newCol = col + j;
                if (isValidCell(newRow, newCol) && grid[newRow][newCol]) {
                    count++;
                }
            }
        }
        return count;
    }

    // 检查一个细胞是否在网格范围内
    private boolean isValidCell(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public static void main(String[] args) {
        GameOfLife game = new GameOfLife(10, 10); // 创建一个10x10的网格
        int generations = 5; // 要模拟的代数

        for (int i = 0; i < generations; i++) {
            System.out.println("Generation " + (i + 1) + ":");
            game.printGrid();
            game.updateGrid();
        }
    }
}
