package Algorithms.PathFinding;

import javafx.application.Platform;

public class PathFinding {

    boolean foundSolution;

    int foundX;
    int foundY;

    Grid grid;

    public PathFinding(Grid g) {
        this.grid = g;
    }

    public void DepthFirst(int x, int y) {
        if (!foundSolution) {
            try {
                if (grid.actualGrid[y][x] == 3) {
                    foundSolution = true;
                    foundX = x;
                    foundY = y;
                    return;
                } else if (grid.actualGrid[y][x] != 1) {
                    if (grid.actualGrid[y][x] != 2) Platform.runLater(() ->grid.setCellDiscovered(x, y));
                    Thread.sleep(150);
                    if (grid.actualGrid[y+1][x] == 0 || grid.actualGrid[y+1][x] == 3) {
                        Thread t1 = new Thread(() ->  DepthFirst(x,y+1));
                        t1.start();
                    }
                    if (grid.actualGrid[y-1][x] == 0 || grid.actualGrid[y-1][x] == 3) {
                        Thread t1 = new Thread(() ->  DepthFirst(x,y-1));
                        t1.start();
                    }
                    if (grid.actualGrid[y][x+1] == 0 || grid.actualGrid[y][x+1] == 3) {
                        Thread t1 = new Thread(() ->  DepthFirst(x+1,y));
                        t1.start();
                    }
                    if (grid.actualGrid[y][x-1] == 0 || grid.actualGrid[y][x-1] == 3) {
                        Thread t1 = new Thread(() ->  DepthFirst(x-1, y));
                        t1.start();
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {} catch (InterruptedException e) {
                e.printStackTrace();
            }
            ;
        }
    }
}
