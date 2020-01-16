package DancingLinks;

/**
 * @author      Lars Quaedvlieg <Larsquaedvlieg@outlook.com>
 * @version     1.0
 * @since       1.0
*/

/**
* Represents a single column node in a 4-way linked list.
* A column node has all properties and methods a node has, the number of nodes under it and a name (unique ID)
*/
class ColumnNode extends Node {
  /**
  * The number of connection in the downwards direction
  */
  int num_nodes_down;
  /**
  * Its name (unique ID)
  */
  String name;

  /**
  * Initializes a new column node with the default Node construction and adds the number of nodes under it, the name and sets it column node it itself.
  * @param n the name of the column node
  */
  public ColumnNode(String n) {
    super();
    this.num_nodes_down = 0;
    this.name = n;
    this.CN = this;
  }

  /**
  * Remove column from the matrix and remove all rows in the column from other column they are in
  */
  public void cover() {
    removeNodeLR();

    for(Node row = this.down; row != this; row = row.down) {
      for (Node rightNode = row.right; rightNode != row; rightNode = rightNode.right) {
        rightNode.removeNodeUD();
        rightNode.CN.num_nodes_down--;
      }
    }
  }

  /**
  * Backtracks the cover, re-adding all nodes to the matrix (inverse method of cover)
  */
  public void uncover() {
    for (Node row = this.up; row != this; row = row.up) {
      for (Node leftNode = row.left; leftNode != row; leftNode = leftNode.left) {
        leftNode.addNodeUD();
        leftNode.CN.num_nodes_down++;
      }
    }
    addNodeLR();
  }

}
