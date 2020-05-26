package DataStructures.Trees;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BinarySearchTree<E> {

    private BSTNode<E> root;
    private final Comparator<E> comparator;

    public BinarySearchTree(Comparator<E> comparator) {
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
        if (root == null) {
            root = node;
        } else {
            root.addChild(node);
        }
    }

    public void delete(E value) {
        BSTNode<E> valueNode = root.findNode(value);
        // If no left child
        if (!valueNode.hasLeftChild()) {
            // We remove it from the tree, it's as simple as that
            int index = valueNode.parent.children.indexOf(valueNode);
            valueNode.parent.children.set(index, null);
            valueNode.parent = null;
        // Otherwise
        } else {
            // We find that node that would follow by using inorder traversal on the right sub tree of our value
            if (valueNode.hasRightChild()) {
                BSTNode<E> followerNode = valueNode.getRightChild().inOrderTraverse().get(0);
                System.out.println(followerNode.getData());
                valueNode.setData(followerNode.getData());

                //TODO: Fix logical error somewhere here

                // Then, we update it in the tree structure
                List<AbstractTreeNode<E>> parentChildren = followerNode.getParent().children;
                int index = parentChildren.indexOf(followerNode);
                parentChildren.set(index, null);
            } else {
                valueNode.setData(valueNode.getLeftChild().getData());
                valueNode.children.get(0).parent = null;
                valueNode.children.set(0, null);
            }
        }
    }

    @SafeVarargs
    public final void add(E... data) {
        for (E dataPoint: data) {
            add(dataPoint);
        }
    }

    @Override
    public String toString() {
        return root.toString();
    }

    public static void main(String[] args) {

        Integer[] data = {5, 2, 7, 1, 4, 6};

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
        for (Integer v: data) {
            System.out.print(v + ": \n");
            searchTree.delete(v);
            searchTree.add(v);
            System.out.println(searchTree);
        }

    }

}
