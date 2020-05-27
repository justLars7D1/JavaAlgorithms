package DataStructures.Trees;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinarySearchTree<E> {

    private BSTNode<E> root;
    private final Comparator<E> comparator;

    public BinarySearchTree(Comparator<E> comparator) {
        this.root = new BSTNode<E>(null, comparator);
        this.comparator = comparator;
    }

    public boolean contains(E value) {
        return root.contains(value);
    }

    public List<E> preOrderTraverse() {
        return root.preOrderTraverse();
    }

    public List<E> postOrderTraverse() {
        return root.postOrderTraverse();
    }

    public List<E> inOrderTraverse() {
        List<E> values = new ArrayList<>();
        for (BSTNode<E> node: root.inOrderTraverse()) {
            values.add(node.getData());
        }
        return values;
    }

    public void add(E data) {
        BSTNode<E> node = new BSTNode<>(data, comparator);
        if (root.isExternal()) {
            node.setParent(root);
            List<AbstractTreeNode<E>> nodes = new ArrayList<>();
            nodes.add(node);
            root.setChildren(nodes);
        } else {
            getRoot().addChild(node);
        }
    }

    public void delete(E value) {
        BSTNode<E> valueNode = getRoot().findNode(value);
        // If no children
        if (valueNode.isExternal() && valueNode != root) {
            deleteNodeFromTree(valueNode);
        // If only one child
        } else if (valueNode.size() == 1) {
            // Get the child, remove the connection with their parent
            BSTNode<E> childNode = (valueNode.hasLeftChild()) ? valueNode.getLeftChild() : valueNode.getRightChild();
            int index = valueNode.getChildren().indexOf(childNode);

            deleteNodeFromTree(childNode);

            // Delete the parent from the tree
            BSTNode<E> parentNode = (BSTNode<E>) valueNode.getParent();
            deleteNodeFromTree(valueNode);

            // And add the child to it's parent
            addNodeToTree(childNode, parentNode, index);
        } else if (valueNode.size() == 2) {
            // Copy the data (value and right child) from it's successor node which is found by inorder traversal of
            // the right subtree to it and delete the successor node from the tree
            BSTNode<E> successorNode = valueNode.getRightChild().inOrderTraverse().get(0);
            AbstractTreeNode<E> rightChild = successorNode.getChildren().get(1);
            valueNode.setData(successorNode.getData());
            deleteNodeFromTree(successorNode);
            valueNode.children.set(1, rightChild);
        }
    }

    private void addNodeToTree(BSTNode<E> node, BSTNode<E> parentNode, int index) {
        if (parentNode == root) {
            parentNode.getChildren().set(0, node);
        } else {
            parentNode.getChildren().set(index, node);
        }
        node.setParent(parentNode);
    }

    private void deleteNodeFromTree(BSTNode<E> node) {
        BSTNode<E> parentNode = (BSTNode<E>) node.getParent();
        int index = parentNode.getChildren().indexOf(node);
        parentNode.children.set(index, null);
        node.setParent(null);
    }

    @SafeVarargs
    public final void add(E... data) {
        for (E dataPoint: data) {
            add(dataPoint);
        }
    }

    @Override
    public String toString() {
        if (getRoot() != null) {
            return getRoot().toString();
        } else {
            return "[]";
        }
    }

    private BSTNode<E> getRoot() {
        return (BSTNode<E>) root.getChildren().get(0);
    }

    public static void main(String[] args) {

        Integer[] data = {5, 2, 4, 6, 8, -1, 45};

        Comparator<Integer> comparator = (o1, o2) -> {
            if (o1 < o2) {
                return -1;
            } else if (o1.equals(o2)) {
                return 0;
            } else {
                return 1;
            }
        };

        BinarySearchTree<Integer> searchTree = new BinarySearchTree<>(comparator);
        searchTree.add(data);

        System.out.println(searchTree);

    }

}
