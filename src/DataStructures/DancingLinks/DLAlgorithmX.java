package DataStructures.DancingLinks;

import java.util.ArrayList;

/**
 * @author      Lars Quaedvlieg <Larsquaedvlieg@outlook.com>
 * @version     1.0
 * @since       1.0
*/


/**
* Represents a dancing links implementation of algorithm X
* The algorithm contains a header and a solution
*/
public class DLAlgorithmX {
  /**
  * The header of the problem matrix (this is the pointer to the first header)
  */
  ColumnNode header;
  /**
  * The solutions of the algorithm
  */
  ArrayList<ArrayList<Node>> solutions = new ArrayList<ArrayList<Node>>();
  /**
  * The number of solutions found by the algorithm
  */
  int num_solutions_found = 0;
  /**
  * The number of solutions to find by the algorithm
  */
  int num_solutions_to_find;

  /**
  * Stores a temporary solution from the algorithm
  */
  private ArrayList<Node> tmp_solution = new ArrayList<Node>();

  /**
  * Creates a new instance of the DLAlgorithmX class that tries to find one solution
  * @param header the header node that gives access to the problem list
  */
  public DLAlgorithmX(ColumnNode header) {
    this.header = header;
    this.num_solutions_to_find = 1;
  }

  /**
  * Creates a new instance of the DLAlgorithmX class
  * @param header the header node that gives access to the problem list
  * @param num_sol_to_find the number of solutions the algorithm has to try to find
  */
  public DLAlgorithmX(ColumnNode header, int num_sol_to_find) {
    this.header = header;
    this.num_solutions_to_find = num_sol_to_find;
  }

  /**
  * Actually algorithm X in action (Dancing Links)
  * Link to original paper: https://arxiv.org/abs/cs/0011047
  * Credits go to D. Knuth for his work on dancing links
  * @param a the "level" of the search
  */
  public void search(int a) {
    if (this.num_solutions_to_find == this.num_solutions_found) {
      //If all solutions that need to be found are found, quit the search
      return;
    }

    if (this.header.right == this.header) {
      //It's found a solution
      ArrayList<Node> copy_of_tmp = new ArrayList(tmp_solution);
      solutions.add(copy_of_tmp);
      this.num_solutions_found++;

    } else {
      //Select nearest column node with lowest number of downward num_nodes_down
      ColumnNode col = selectColumnNode();
      //Cover it and remove from the number of nodes under the header

      col.cover();
      //Go down the column and stop at every node
      for (Node rowNode = col.down; rowNode != col; rowNode = rowNode.down) {

        //Add this row node to the solution
        tmp_solution.add(rowNode);

        //Go to the right of the matrix for this current row
        for (Node rightNode = rowNode.right; rightNode != rowNode; rightNode = rightNode.right) {
          //Cover it's column and remove from the number of nodes under the header
          rightNode.CN.cover();
          this.header.num_nodes_down--;
        }

        //Go deeper down :)
        search(a+1);

        //Remove from solution
        rowNode = tmp_solution.remove(tmp_solution.size()-1);
        col = rowNode.CN;

        //Do the complete oposite of what we just did, so trace back for the row and uncover the column
        for (Node leftNode = rowNode.left; leftNode != rowNode; leftNode = leftNode.left) {
          leftNode.CN.uncover();
          this.header.num_nodes_down++;
        }

      }

      col.uncover();
      this.header.num_nodes_down++;

    }

  }

  /**
  * Create method to transform the solution into a normal pentomino grid
  * @param sol an arraylist containing the solution nodes
  * @param field the playing field
  */
  public void solutionToField(ArrayList<Node> sol, int[][] field) {
    //For every solution node, get the columns (cells) they are in, and the ID and then place them on the field
    for (Node n: sol) {
      ArrayList<String> tmp_list = new ArrayList<String>();
      n = n.left;
      int pentID = -1;
      if (n.CN.name.charAt(0) == '#') {
        pentID = Integer.parseInt(n.CN.name.substring(1));
      } else {
        tmp_list.add(n.CN.name);
      }
      for(Node leftNode = n.left; leftNode != n; leftNode = leftNode.left) {
        if (leftNode.CN.name.charAt(0) == '#') {
          pentID = Integer.parseInt(leftNode.CN.name.substring(1));
        } else {
          tmp_list.add(leftNode.CN.name);
        }
      }

      for (String col: tmp_list) {
        String[] parts = col.split("-");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        field[x][y] = pentID;
      }

    }
  }

  /**
  * Get the first column node with the fewest number of elements
  * @return the first column node with the fewest number of elements
  */
  private ColumnNode selectColumnNode() {
    int min = Integer.MAX_VALUE;
    ColumnNode res = null;
    for (ColumnNode C = (ColumnNode) this.header.right; C != this.header; C = (ColumnNode) C.right) {
      if (C.num_nodes_down < min) {
        min = C.num_nodes_down;
        res = C;
      }
    }
    return res;
  }

}
