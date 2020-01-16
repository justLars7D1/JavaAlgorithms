package DancingLinks;

/**
 * @author      Lars Quaedvlieg <Larsquaedvlieg@outlook.com>
 * @version     1.0
 * @since       1.0
*/


/**
* Represents a single node in a 4-way linked list.
* A node has a left-, right-, up-, downwards connection and has a column node (the node of the column it is in).
*/
public class Node {
  /**
  * The connections to the node on the left, right up and downward direction
  */
  Node left, right, up, down;
  /**
  * Its column node
  */
  ColumnNode CN;

  /**
  * Creates a new node and connects it only to itself.
  */
  public Node() {
    this.left = this;
    this.right = this;
    this.up = this;
    this.down = this;
  }

  /**
  * Creates a new node and connects it to itself, but also adds a parent node
  * @param C the parent node
  */
  public Node(ColumnNode C) {
    this.CN = C;
    this.left = this;
    this.right = this;
    this.up = this;
    this.down = this;
  }

  /**
  * Attaches a node "n" down the current node
  * @param n the node that gets connected down
  * @return the node attached down
  */
  public Node attachDown(Node n) {
    n.down = this.down;
    n.down.up = n;
    n.up = this;
    this.down = n;
    return n;
  }

  /**
  * Attaches a node "n" on the right of the current node
  * @param n the node that gets connected on the right
  * @return the node attached right
  */
  public Node attachRight(Node n) {
    n.right = this.right;
    n.right.left = n;
    n.left = this;
    this.right = n;
    return n;
  }

  /**
  * Remove the left and right connection of the current node (disconnect it left and right)
  */
  public void removeNodeLR() {
    this.right.left = this.left;
    this.left.right = this.right;
  }

  /**
  * Remove the up and down connection of the current node (disconnect it up and down)
  */
  public void removeNodeUD() {
    this.up.down = this.down;
    this.down.up = this.up;
  }

  /**
  * Add the left and right connection of the current node (connect it left and right)
  */
  public void addNodeLR() {
    this.right.left = this;
    this.left.right = this;
  }

  /**
  * Add the up and down connection of the current node (connect it up and down)
  */
  public void addNodeUD() {
    this.up.down = this;
    this.down.up = this;
  }

}
