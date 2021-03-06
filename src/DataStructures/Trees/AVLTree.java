package DataStructures.Trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a linked-based implementation of an AVL-Tree using nodes that have a left-, right child and a parent
 *
 * @param <E>
 */
public class AVLTree<E> {

    /**
     * The comparator which we use for the ordering of the tree
     */
    private final Comparator<E> comparator;
    /**
     * The fake root node we create as a starting point of the tree, with value "null"
     */
    private AVLNode root;

    /**
     * Constructor - Sets the comparator and adds the null-value to the root node
     *
     * @param comparator The comparator
     */
    public AVLTree(Comparator<E> comparator) {
        this.root = new AVLNode(null);
        this.comparator = comparator;
    }

    /**
     * Add a list of elements to the tree
     *
     * @param data The list of elements
     */
    @SafeVarargs
    public final void add(E... data) {
        for (E dataPoint : data) {
            add(dataPoint);
        }
    }

    /**
     * Adds a new element of data to the tree
     *
     * @param data The data
     */
    public void add(E data) {
        AVLNode node = new AVLNode(data);
        if (root.isExternal()) {
            node.setParent(root);
            List<AbstractTreeNode<E>> nodes = new ArrayList<>();
            nodes.add(node);
            root.setChildren(nodes);
        } else {
            getRoot().addChild(node);
        }
    }

    /**
     * Delete a value from the tree
     *
     * @param value The value to delete
     */
    public void delete(E value) {
        AVLNode valueNode = getRoot().findNode(value);
        AVLNode parentOfValueNode = (AVLNode) valueNode.getParent();

        // If no children
        if (valueNode.isExternal() && valueNode != root) {
            deleteNodeFromTree(valueNode);
            // If only one child
        } else if (valueNode.size() == 1) {
            // Get the child, remove the connection with their parent
            AVLNode childNode = (valueNode.hasLeftChild()) ? valueNode.getLeftChild() : valueNode.getRightChild();

            deleteNodeFromTree(childNode);

            // Delete the parent from the tree
            AVLNode parentNode = (AVLNode) valueNode.getParent();
            int index = parentNode.getChildren().indexOf(valueNode);
            deleteNodeFromTree(valueNode);

            // And add the child to it's parent
            addNodeToTree(childNode, parentNode, index);
        } else if (valueNode.size() == 2) {
            // Copy the data (value and right child) from it's successor node which is found by inorder traversal of
            // the right subtree to it and delete the successor node from the tree
            AVLNode successorNode = valueNode.getRightChild().inOrderTraverse().get(0);
            AVLNode successorParent = (AVLNode) successorNode.getParent();
            AbstractTreeNode<E> rightChild = successorNode.getChildren().get(1);
            int successorIndex = successorNode.getParent().children.indexOf(successorNode);

            valueNode.setData(successorNode.getData());
            deleteNodeFromTree(successorNode);

            successorParent.children.set(successorIndex, rightChild);
            if (rightChild != null) rightChild.setParent(successorParent);

        }

        parentOfValueNode.rebalanceDeletion();

    }

    /**
     * Check if the tree contains a value
     *
     * @param value The value to look for
     * @return A boolean, specifying whether the value was found or not
     */
    public boolean contains(E value) {
        return getRoot().contains(value);
    }

    /**
     * Find all values in the tree between a given range
     *
     * @param minValue The (inclusive) minimal value
     * @param maxValue The (inclusive) maximum value
     * @return All elements within the range
     */
    public List<E> findInRange(E minValue, E maxValue) {
        List<E> results = new ArrayList<>();
        List<AVLNode> nodes = getRoot().findInRange(minValue, maxValue);
        for (AVLNode node : nodes) {
            results.add(node.getData());
        }
        return results;
    }

    /**
     * Traverses the tree in a pre-order way
     *
     * @return The list of elements sorted in a pre-order way
     */
    public List<E> preOrderTraverse() {
        return root.preOrderTraverse();
    }

    /**
     * Traverses the tree in a post-order way
     *
     * @return The list of elements sorted in a post-order way
     */
    public List<E> postOrderTraverse() {
        return root.postOrderTraverse();
    }

    /**
     * Traverses the tree in a in-order way
     *
     * @return The list of elements sorted in a in-order way
     */
    public List<E> inOrderTraverse() {
        List<E> values = new ArrayList<>();
        for (AVLNode node : getRoot().inOrderTraverse()) {
            values.add(node.getData());
        }
        return values;
    }


    /**
     * Get the actual root, not the fake null one
     *
     * @return The actual root
     */
    private AVLNode getRoot() {
        return (AVLNode) root.getChildren().get(0);
    }

    /**
     * Adds a node to the tree, enforcing that the first node to be added to the tree is the root node
     *
     * @param node       The node to add
     * @param parentNode It's parent node
     * @param index      The index of the node to the parent
     */
    private void addNodeToTree(AVLNode node, AVLNode parentNode, int index) {
        if (parentNode == root) {
            parentNode.getChildren().set(0, node);
        } else {
            parentNode.getChildren().set(index, node);
        }
        node.setParent(parentNode);
    }

    /**
     * Deletes a node from the tree
     *
     * @param node The node to delete from the tree
     */
    private void deleteNodeFromTree(AVLNode node) {
        AVLNode parentNode = (AVLNode) node.getParent();
        int index = parentNode.getChildren().indexOf(node);
        parentNode.children.set(index, null);
        node.setParent(null);
    }

    /**
     * String representation of the tree
     *
     * @return The representation
     */
    @Override
    public String toString() {
        if (getRoot() != null) {
            return getRoot().toString();
        } else {
            return "[]";
        }
    }

    /**
     * This class represents a node in a Binary Search Tree (or more specifically, an AVL-tree)
     */
    class AVLNode extends AbstractTreeNode<E> {

        /**
         * The height of the node within the tree (lowest node has height 1) - Defined by 1 + max(heightLeftNode, heightRightNode)
         */
        private int height = 1;

        // We say that the left child is children[0] and the right child is children[1].

        /**
         * Constructor
         *
         * @param data The data that the node contains
         */
        public AVLNode(E data) {
            super(data, Arrays.asList(null, null));
        }

        /**
         * Adds a child to the node's subtree
         *
         * @param nodeToAdd The node to add to the subtree
         */
        public void addChild(AVLNode nodeToAdd) {
            // First we insert it like in a BST
            int comparison = comparator.compare(data, nodeToAdd.data);
            if (comparison <= 0) {
                if (!hasRightChild()) {
                    children.set(1, nodeToAdd);
                    nodeToAdd.parent = this;
                } else {
                    getRightChild().addChild(nodeToAdd);
                }
            } else {
                if (!hasLeftChild()) {
                    children.set(0, nodeToAdd);
                    nodeToAdd.parent = this;
                } else {
                    getLeftChild().addChild(nodeToAdd);
                }
            }

            // AVL PART STARTS ///

            // We update the height for the current node and the ones on it's path
            updateHeight();

            // Now we check if it's unbalanced
            int balanceNumber = calculateBalance();
            E dataToAdd = nodeToAdd.getData();
            restoreBalance(balanceNumber, dataToAdd, this);

            // AVL PART ENDS //

        }

        /**
         * Find all nodes that have a value within a range of values of the current subtree
         *
         * @param minValue The (inclusive) minimal value
         * @param maxValue The (inclusive) maximum value
         * @return The list of nodes
         */
        public List<AVLNode> findInRange(E minValue, E maxValue) {
            int minComp = comparator.compare(data, minValue);
            int maxComp = comparator.compare(data, maxValue);

            if (minComp >= 0 && 0 >= maxComp) {
                ArrayList<AVLNode> resultList = new ArrayList<>();
                resultList.add(this);
                if (hasLeftChild()) {
                    List<AVLNode> LList = getLeftChild().findInRange(minValue, maxValue);
                    resultList.addAll(LList);
                }
                if (hasRightChild()) {
                    List<AVLNode> RList = getRightChild().findInRange(minValue, maxValue);
                    resultList.addAll(RList);
                }
                return resultList;
            } else if (minComp < 0 && hasRightChild()) {
                return getRightChild().findInRange(minValue, maxValue);
            } else if (maxComp > 0 && hasLeftChild()) {
                return getLeftChild().findInRange(minValue, maxValue);
            }

            return new ArrayList<>();
        }

        /**
         * Find a node that contains a specific value - We assume the value exists within the tree
         *
         * @param value The value to find then node for
         * @return THe node with the value
         */
        public AVLNode findNode(E value) {
            int comparison = comparator.compare(data, value);
            if (comparison == 0) {
                return this;
            } else if (comparison < 0 && hasRightChild()) {
                return getRightChild().findNode(value);
            } else if (comparison > 0 && hasLeftChild()) {
                return getLeftChild().findNode(value);
            }
            return null;
        }

        /**
         * Check if the subtree of a node contains a value
         *
         * @param value The value to look for
         * @return Whether the node contains the value
         */
        public boolean contains(E value) {
            int comparison = comparator.compare(data, value);
            if (comparison == 0) {
                return true;
            } else if (comparison < 0 && hasRightChild()) {
                return getRightChild().contains(value);
            } else if (comparison > 0 && hasLeftChild()) {
                return getLeftChild().contains(value);
            } else {
                return false;
            }
        }

        /**
         * Helper method for recursive in-order traversal method
         *
         * @param valList The list of values to add to
         */
        private void inOrderHelper(List<AVLNode> valList) {
            if (hasLeftChild()) getLeftChild().inOrderHelper(valList);
            valList.add(this);
            if (hasRightChild()) getRightChild().inOrderHelper(valList);
        }

        /**
         * Traverse the subtree of a current node in an in-order manner
         *
         * @return The list containing all values in an in-order sorted way
         */
        public List<AVLNode> inOrderTraverse() {
            List<AVLNode> result = new ArrayList<>();
            inOrderHelper(result);
            return result;
        }

        /**
         * Restore the balance in the tree in an AVL-manner
         *
         * @param balanceNumber The balance number (difference between height of left and right nodes)
         * @param dataToAdd     The data to add to the tree
         * @param subTreeRoot   The root of the subtree
         */
        private void restoreBalance(int balanceNumber, E dataToAdd, AVLNode subTreeRoot) {
            if (balanceNumber > 1 && hasLeftChild() && comparator.compare(dataToAdd, getLeftChild().getData()) < 0) {
                //Left Left case
                rightRotation(subTreeRoot);
            }
            if (balanceNumber < -1 && hasRightChild() && comparator.compare(dataToAdd, getRightChild().getData()) > 0) {
                //Right Right case
                leftRotation(subTreeRoot);
            }
            if (balanceNumber > 1 && hasLeftChild() && comparator.compare(dataToAdd, getLeftChild().getData()) > 0) {
                //Left Right case
                if (subTreeRoot.hasLeftChild()) leftRotation(subTreeRoot.getLeftChild());
                rightRotation(subTreeRoot);
            }
            if (balanceNumber < -1 && hasRightChild() && comparator.compare(dataToAdd, getRightChild().getData()) < 0) {
                //Right Left case
                if (subTreeRoot.hasRightChild()) rightRotation(subTreeRoot.getRightChild());
                leftRotation(subTreeRoot);
            }
        }

        /**
         * Rebalance the BST after a deletion, to make it an AVL-tree again
         */
        private void rebalanceDeletion() {

            if (this != root) {

                updateHeight();

                int balanceNumber = calculateBalance();
                AVLNode leftNode = getLeftChild();
                AVLNode rightNode = getRightChild();

                // Left Left Case
                if (balanceNumber > 1 && leftNode.calculateBalance() >= 0) {
                    rightRotation(this);
                }

                // Left Right Case
                if (balanceNumber > 1 && leftNode.calculateBalance() < 0) {
                    leftRotation(leftNode);
                    rightRotation(this);
                }

                // Right Right Case
                if (balanceNumber < -1 && rightNode.calculateBalance() < 0) {
                    leftRotation(this);
                }

                // Right Left Case
                if (balanceNumber < -1 && rightNode.calculateBalance() > 0) {
                    rightRotation(rightNode);
                    leftRotation(this);
                }

                if (getParent() != root) ((AVLNode) getParent()).rebalanceDeletion();

            }

        }

        /**
         * Update the height of the node within the tree
         */
        private void updateHeight() {
            int heightLeft = (hasLeftChild()) ? getHeight(getLeftChild()) : 0;
            int heightRight = (hasRightChild()) ? getHeight(getRightChild()) : 0;
            height = 1 + Math.max(heightLeft, heightRight);
        }

        /**
         * Calculates the balance of the AVL-subtree
         *
         * @return The balance
         */
        private int calculateBalance() {
            return getHeight(getLeftChild()) - getHeight(getRightChild());
        }

        /**
         * Perform a right rotation in an AVL-manner
         *
         * @param subTreeRoot The subtree to perform the rotation on
         */
        private void rightRotation(AVLNode subTreeRoot) {

            AVLNode rootParent = (AVLNode) subTreeRoot.getParent();
            int index = rootParent.children.indexOf(subTreeRoot);

            AVLNode left = subTreeRoot.getLeftChild();
            AVLNode rightOfLeft = left.getRightChild();

            // The actual rotation
            left.children.set(1, subTreeRoot);
            subTreeRoot.children.set(0, rightOfLeft);

            // Update the parents
            left.parent = rootParent;
            subTreeRoot.parent = left;
            if (rightOfLeft != null) rightOfLeft.parent = subTreeRoot;

            // We then update their height correspondingly
            subTreeRoot.updateHeight();
            left.updateHeight();

            // Add the new one actual root to the root
            rootParent.children.set(index, left);
        }

        /**
         * Perform a left rotation in an AVL-manner
         *
         * @param subTreeRoot The subtree to perform the rotation on
         */
        private void leftRotation(AVLNode subTreeRoot) {

            AVLNode rootParent = (AVLNode) subTreeRoot.getParent();
            int index = rootParent.children.indexOf(subTreeRoot);

            AVLNode right = subTreeRoot.getRightChild();
            AVLNode leftOfRight = right.getLeftChild();

            // The actual rotation
            right.children.set(0, subTreeRoot);
            subTreeRoot.children.set(1, leftOfRight);

            // Update the parents
            right.parent = rootParent;
            subTreeRoot.parent = right;
            if (leftOfRight != null) leftOfRight.parent = subTreeRoot;

            // We then update their height correspondingly
            subTreeRoot.updateHeight();
            right.updateHeight();

            rootParent.children.set(index, right);
        }

        /**
         * Get the left child
         *
         * @return The left child
         */
        public AVLNode getLeftChild() {
            return (AVLNode) children.get(0);
        }

        /**
         * Get the right child
         *
         * @return The right child
         */
        public AVLNode getRightChild() {
            return (AVLNode) children.get(1);
        }

        /**
         * Check if there exists a left child
         *
         * @return Whether there exists a left child or not
         */
        public boolean hasLeftChild() {
            return children.get(0) != null;
        }

        /**
         * Check if there exists a right child
         *
         * @return Whether there exists a right child or not
         */
        public boolean hasRightChild() {
            return children.get(1) != null;
        }

        /**
         * Get the data of a node
         *
         * @return The data
         */
        @Override
        protected E getData() {
            return super.getData();
        }

        /**
         * Set the data of a node
         *
         * @param data The data
         */
        @Override
        protected void setData(E data) {
            super.setData(data);
        }

        /**
         * Get the number of children that a node has
         *
         * @return The number of children
         */
        @Override
        protected int size() {
            int numNull = 0;
            for (AbstractTreeNode<E> child : children) {
                if (child == null) numNull++;
            }
            return 2 - numNull;
        }

        /**
         * Check if the node is the root
         *
         * @return Whether the node is a root or not
         */
        @Override
        protected boolean isRoot() {
            return super.isRoot();
        }

        /**
         * Check if the node is external
         *
         * @return Whether the node is external or not
         */
        @Override
        protected boolean isExternal() {
            return super.isExternal();
        }

        /**
         * Check if the node is internal
         *
         * @return Whether the node is internal or not
         */
        @Override
        protected boolean isInternal() {
            return !isExternal();
        }

        /**
         * Get the parent of a node
         *
         * @return The parent of a node
         */
        @Override
        protected AbstractTreeNode<E> getParent() {
            return super.getParent();
        }

        /**
         * Get the height of a node
         *
         * @param node The node for which the height must be found
         * @return The height of the node
         */
        private int getHeight(AVLNode node) {
            if (node == null) return 0;
            return node.height;
        }

        /**
         * String representation of the node's subtree
         *
         * @return The representation
         */
        @Override
        public String toString() {
            return "Root: " + toString(1);
        }

        /**
         * Recursive method for the toString method
         *
         * @param depth The depth of the current node
         * @return The representation
         */
        @Override
        protected String toString(int depth) {
            StringBuilder strData = new StringBuilder(data + "\n");
            for (int j = 0; j < children.size(); j++) {
                AbstractTreeNode<E> child = children.get(j);
                if (child != null) {
                    strData.append("\t".repeat(Math.max(0, depth)));
                    strData.append("Child ").append(j).append(": ").append(child.toString(depth + 1));
                }
            }
            return strData.toString();
        }
    }

}
