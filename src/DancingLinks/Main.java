package DancingLinks;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * @author      Lars Quaedvlieg <Larsquaedvlieg@outlook.com>
 * @version     1.0
 * @since       1.0
*/

/**
* This class represents the main part of the application
*/
public class Main {

    /**
    * Represents the horizontal grid size
    */
    public static int horizontalGridSize;

    /**
    * Represents the vertical grid size
    */
    public static int verticalGridSize;

    /**
    * Represents the interval between showing the solutions
    */
    public static int solution_interval;

    /**
    * Represents the number of solutions to show
    */
    public static int num_solutions_to_show;

    /**
    * Represents the input of pentominoes
    */
    public static char[] input;

    /**
    * The UI of the application
    */
    public static UI ui;

    /**
    * The search method that executes the core of the application
    */
    public static void search() {
        //Check if each cell can be filled, if not, give error :(
        if (horizontalGridSize*verticalGridSize != input.length*5) {
          System.out.println("There are no solutions for this setup");
          return;
        }

        // Initialize an empty board
        int[][] field = new int[horizontalGridSize][verticalGridSize];
        initialize_field(field);

        //Generate the linked matrix :O
        ColumnNode header = generate_linked_matrix(field);

        //Initialize and execute the search, ulala
        DLAlgorithmX algorithm = new DLAlgorithmX(header, num_solutions_to_show);
        algorithm.search(0);

        //Check if there's no solution
        if (algorithm.num_solutions_found == 0) {
          System.out.println("There are no solutions for this setup");
          return;
        }

        //For all found solutions, show them with the GUI for a bit
        for (int i = 0; i < algorithm.num_solutions_found; i++) {
          algorithm.solutionToField(algorithm.solutions.get(i), field);
          ui.setState(field);
          try {
            Thread.sleep(solution_interval);
          } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
          }
        }

    }

    /**
    * Transform the character of a pentomino to it's corresponding ID
    * @param character the character of a pentomino
    * @return the pentomino ID
    */
    private static int characterToID(char character) {
      character = Character.toUpperCase(character);
    	int pentID = -1;
    	if (character == 'X') {
    		pentID = 0;
    	} else if (character == 'I') {
    		pentID = 1;
    	} else if (character == 'Z') {
    		pentID = 2;
    	} else if (character == 'T') {
    		pentID = 3;
    	} else if (character == 'U') {
    		pentID = 4;
     	} else if (character == 'V') {
     		pentID = 5;
     	} else if (character == 'W') {
     		pentID = 6;
     	} else if (character == 'Y') {
     		pentID = 7;
    	} else if (character == 'L') {
    		pentID = 8;
    	} else if (character == 'P') {
    		pentID = 9;
    	} else if (character == 'N') {
    		pentID = 10;
    	} else if (character == 'F') {
    		pentID = 11;
    	}
    	return pentID;
    }

    /**
    * GENERATE THE LINKED MATRIX <33333
    * @param field the playing field (ARE U READY TO RUMBLLEEE???)
    * @return the header node of the linked matrix
    */
    private static ColumnNode generate_linked_matrix(int[][] field) {

      //Make a header node and an arraylist to store the other column nodes in
      ColumnNode headerNode = new ColumnNode("header");
      ArrayList<ColumnNode> columnNodes = new ArrayList<ColumnNode>();

      //Iterate over all cells in the field and add a column node each representing a cell, also attach them to each other
      for (int i = 0; i < field.length; i++) {
        for (int j = 0; j < field[0].length; j++) {
          ColumnNode tmp_node = new ColumnNode(Integer.toString(i)+"-"+Integer.toString(j));
          columnNodes.add(tmp_node);
          headerNode = (ColumnNode) headerNode.attachRight(tmp_node);
        }
      }

      //Iterate over all input pentominoes
  		for (int i = 0; i < input.length; i++) {

        //Add a column representing a pentomino ID
        int pentID = characterToID(input[i]);
        ColumnNode tmp_node = new ColumnNode("#"+Integer.toString(pentID));
        columnNodes.add(tmp_node);
        headerNode = (ColumnNode) headerNode.attachRight(tmp_node);

        if (i+1 == input.length) {
          headerNode = headerNode.right.CN;
        }

        //Iterate over all permutations of the current pentomino
        for (int[][] pieceToPlace: PentominoDatabase.data[pentID]) {

          //Generate the pentomino to be in every possible position in the field
          for (int y = 0; y <= (field[0].length-pieceToPlace[0].length); y++) {
            for (int x = 0; x <= (field.length-pieceToPlace.length); x++) {
              initialize_field(field);
              addPiece(field, pieceToPlace, pentID, x, y);

              Node previousNode = null;
              //For all column nodes, check if for the current permutation of the pentomino, there should be a node linking to it
              //Also attach them to each other
              for (ColumnNode node: columnNodes) {
                //I don't like this if-statement either, but we need it :(
                //It checks if the column isn't an (ID of a pentomino and it has a pentomino in the field) OR (it is an ID and it's equal to the pentomino's ID)
                String[] parts = node.name.split("-");
                if ((node.name.charAt(0) != '#' && field[Integer.parseInt(parts[0])][Integer.parseInt(parts[1])] != -1) || (node.name.charAt(0) == '#' && node.name.substring(1).equals(Integer.toString(pentID)))) {
                  Node newNode = new Node(node);
                  if (previousNode == null) {
                    previousNode = newNode;
                  }
                  node.up.attachDown(newNode);
                  previousNode = previousNode.attachRight(newNode);
                  node.num_nodes_down++;
                }
              }

            }
          }

        }

      }

      //Return the header node so we can access the linked matrix
      return headerNode;

    }

    /**
    * Adds a pentomino to the position on the field (overriding current board at that position)
    * @param field the playing field
    * @param piece a pentomino piece
    * @param pieceID the ID of a piece
    * @param x the x-coordinate to place the piece on
    * @param y the y-coordinate to place the piece on
    */
    public static void addPiece(int[][] field, int[][] piece, int pieceID, int x, int y) {
        for(int i = 0; i < piece.length; i++) // loop over x position of pentomino
        {
            for (int j = 0; j < piece[i].length; j++) // loop over y position of pentomino
            {
                if (piece[i][j] == 1)
                {
                    // Add the ID of the pentomino to the board if the pentomino occupies this square
                    field[x + i][y + j] = pieceID;
                }
            }
        }
    }

    /**
    * Empty the field (set all values to -1)
    * @param field the playing field
    */
    public static void initialize_field(int[][] field) {
      for(int i = 0; i < field.length; i++)
      {
          for(int j = 0; j < field[i].length; j++)
          {
              // -1 in the state matrix corresponds to empty square
              // Any positive number identifies the ID of the pentomino
            field[i][j] = -1;
          }
      }
    }


    /**
    * Main function. Needs to be executed to start the brute force algorithm
    * @param args we don't use this :(
    */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Horizontal grid size: ");
    		horizontalGridSize = in.nextInt();
    		System.out.print("Vertical grid size: ");
    		verticalGridSize = in.nextInt();

        System.out.println();
        System.out.print("Number of solutions to show: ");
    		num_solutions_to_show = in.nextInt();
        System.out.print("Interval between solution (in ms): ");
    		solution_interval = in.nextInt();

        // gets the available pentominoes and puts them in an array
    		System.out.println();
    		System.out.println("Choose " + ((horizontalGridSize*verticalGridSize) / 5) + " characters from F,I,L,N,P,T,U,V,W,X,Y,Z (eg. \"FNPVW\")");
    		System.out.print("Pentominoes to use: ");
    		input = in.next().toCharArray();

        ui = new UI(horizontalGridSize, verticalGridSize, 50);

        search();

    }
}
