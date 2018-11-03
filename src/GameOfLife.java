public interface GameOfLife {

    // creates new game boards with a given width and height, all cells are dead
    void init(int w, int h);


    //sets the cell state with coordinates (x, y) to live or dead depending on the alive parameter
    void updateCell(int x, int y, boolean alive);

    //returns information if the cell with coordinates (x, y) is alive or dead
    boolean cellState(int x, int y);

    //calculates the new state of the game based on the current state
    void next();

}