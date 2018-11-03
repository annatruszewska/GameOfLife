public class GameOfLifeImpl implements GameOfLife {

    private boolean[][] cells;
    private boolean[][] newCells;
    private boolean[][] tmpCells;

    @Override
    public void init(int w, int h) {
        cells = new boolean[w][h];
        newCells = new boolean[w][h];
    }

    @Override
    public void updateCell(int x, int y, boolean alive) {
        cells[x][y] = alive;
    }

    @Override
    public boolean cellState(int x, int y) {
        return cells[x][y];
    }

    @Override
    public void next() {
        for (int x = 0; x < cells.length; x++) {
            boolean[] newCellsColumn = newCells[x];
            boolean[] cellsColumn = cells[x];
            for (int y = 0; y < cells[0].length; y++) {
                int liveNeighbours = liveNeighbours(x, y);
                newCellsColumn[y] = liveNeighbours == 3 || (liveNeighbours == 2 && cellsColumn[y]);
            }
        }
        tmpCells = cells;
        cells = newCells;
        newCells = tmpCells;
    }

    private int liveNeighbours(int x, int y) {
        int number = 0;
        int xStart = Math.max(x - 1, 0);
        int xEnd = Math.min(x + 1, cells.length - 1);
        int yStart = Math.max(y - 1, 0);
        int yEnd = Math.min(y + 1, cells[0].length - 1);
        for (int i = xStart; i <= xEnd; i++) {
            boolean[] cellsColumn = cells[i];
            for (int j = yStart; j <= yEnd; j++)
                if (cellsColumn[j])
                    number++;
        }
        if (cells[x][y])
            number--;
        return number;
    }

}