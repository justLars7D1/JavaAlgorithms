package PathFinding;

import javafx.animation.SequentialTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class Grid extends Scene {

    private static final double sizePerGridX = 10;
    private static final double sizePerGridY = 10;

    byte[][] actualGrid;

    Group root;
    Group cellsInGUI;

    int startCellX;
    int endCellX;
    int startCellY;
    int endCellY;

    PathFinding p;

    public Grid() {
        super(new Group(), Settings.numCellsX*sizePerGridX, Settings.numCellsY*sizePerGridY);
        p = new PathFinding(this);
        actualGrid = new byte[Settings.numCellsY][Settings.numCellsX];
        root = (Group) getRoot();
        cellsInGUI = new Group();
        root.getChildren().add(cellsInGUI);
        setupGrid();

        setOnMouseDragged(e -> {
            if (e.getX() <= 0 || e.getX() >= getWidth() || e.getY() <= 0 || e.getY() >= getHeight()) return;
            int cellX = (int)(e.getX()/sizePerGridX);
            int cellY = (int)(e.getY()/sizePerGridY);
            if (actualGrid[cellY][cellX] == 2 || actualGrid[cellY][cellX] == 3) return;
            byte value = 0;
            boolean isClicked = false;
            if (e.isPrimaryButtonDown()) {
                value = 1;
                isClicked = true;
            }
            actualGrid[cellY][cellX] = value;
            for(Node n: cellsInGUI.getChildren()) {
                Rectangle r = (Rectangle) n;
                if(e.getX() >= r.getX() && e.getX() <= r.getX()+r.getWidth() && e.getY() >= r.getY() && e.getY() <= r.getY()+r.getHeight())
                    r.setVisible(isClicked);
            }
        });

        setOnMouseClicked(e -> {
            if (e.getX() <= 0 || e.getX() >= getWidth() || e.getY() <= 0 || e.getY() >= getHeight()) return;
            boolean isStartAlready = false;
            boolean isEndAlready = false;
            for(byte[] row: actualGrid) {
                for(byte cell: row) {
                    if (cell == 2) isStartAlready = true;
                    else if (cell == 3) isEndAlready = true;
                    if (isEndAlready && isStartAlready) break;
                }
            }

            int cellX = (int)(e.getX()/sizePerGridX);
            int cellY = (int)(e.getY()/sizePerGridY);
            Rectangle currentCell = new Rectangle();
            for (Node n: cellsInGUI.getChildren()) {
                Rectangle r = (Rectangle) n;
                if(e.getX() >= r.getX() && e.getX() <= r.getX()+r.getWidth() && e.getY() >= r.getY() && e.getY() <= r.getY()+r.getHeight()) {
                    currentCell = r;
                    break;
                }
            }
            if (actualGrid[cellY][cellX] == 0 && e.getButton() == MouseButton.PRIMARY) {
                if (!isStartAlready) {
                    actualGrid[cellY][cellX] = 2;
                    startCellX = cellX;
                    startCellY = cellY;
                    currentCell.setFill(Color.ORANGE);
                    currentCell.setVisible(true);
                }
            } else if (actualGrid[cellY][cellX] == 0 && e.getButton() == MouseButton.SECONDARY) {
                if (!isEndAlready) {
                    actualGrid[cellY][cellX] = 3;
                    endCellX = cellX;
                    endCellY = cellY;
                    currentCell.setFill(Color.DARKBLUE);
                    currentCell.setVisible(true);
                }
            } else if (actualGrid[cellY][cellX] == 2 || actualGrid[cellY][cellX] == 3) {
                actualGrid[cellY][cellX] = 0;
                endCellX = 0;
                endCellY = 0;
                currentCell.setFill(Color.BLACK);
                currentCell.setVisible(false);
            }

        });

        setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.D) {
                Thread t = new Thread(() -> p.DepthFirst(startCellX, startCellY));
                t.start();
            }
        });

    }

    public void setupGrid() {
        for(int y = 0; y < Settings.numCellsY; y++) {
            root.getChildren().add(new Line(0, y*sizePerGridY, getWidth(), y*sizePerGridY));
        }
        for(int x = 0; x < Settings.numCellsX; x++) {
            root.getChildren().add(new Line(x*sizePerGridX, 0, x*sizePerGridX, getHeight()));
        }
        for(int y = 0; y < Settings.numCellsY; y++) {
            for(int x = 0; x < Settings.numCellsX; x++) {
                Rectangle r = new Rectangle(x*sizePerGridX, y*sizePerGridY, sizePerGridX, sizePerGridY);
                r.setVisible(false);
                cellsInGUI.getChildren().add(r);
            }
        }
    }



    public void setCellDiscovered(int x, int y) {
        actualGrid[y][x] = 4;
        Rectangle corrRect = new Rectangle();
        for(Node n: cellsInGUI.getChildren()) {
            Rectangle r = (Rectangle) n;
            if (x*sizePerGridX == r.getX() && y*sizePerGridY == r.getY()) {
                corrRect = r;
                break;
            }
        }
        corrRect.setFill(Color.LIGHTGREEN);
        corrRect.setVisible(true);
    }

}
