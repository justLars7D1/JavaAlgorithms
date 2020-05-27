package DataStructures.Trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BinarySearchTree<E> {

    private BSTNode root;
    private final Comparator<E> comparator;

    public BinarySearchTree(Comparator<E> comparator) {
        this.root = new BSTNode(null, comparator);
        this.comparator = comparator;
    }

    public List<E> preOrderTraverse() {
        return root.preOrderTraverse();
    }

    public List<E> postOrderTraverse() {
        return root.postOrderTraverse();
    }

    public List<E> inOrderTraverse() {
        List<E> values = new ArrayList<>();
        for (BSTNode node: root.inOrderTraverse()) {
            values.add(node.getData());
        }
        return values;
    }

    public void add(E data) {
        BSTNode node = new BSTNode(data, comparator);
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
        BSTNode valueNode = getRoot().findNode(value);
        // If no children
        if (valueNode.isExternal() && valueNode != root) {
            deleteNodeFromTree(valueNode);
        // If only one child
        } else if (valueNode.size() == 1) {
            // Get the child, remove the connection with their parent
            BSTNode childNode = (valueNode.hasLeftChild()) ? valueNode.getLeftChild() : valueNode.getRightChild();
            int index = valueNode.getChildren().indexOf(childNode);

            deleteNodeFromTree(childNode);

            // Delete the parent from the tree
            BSTNode parentNode = (BSTNode) valueNode.getParent();
            deleteNodeFromTree(valueNode);

            // And add the child to it's parent
            addNodeToTree(childNode, parentNode, index);
        } else if (valueNode.size() == 2) {
            // Copy the data (value and right child) from it's successor node which is found by inorder traversal of
            // the right subtree to it and delete the successor node from the tree
            BSTNode successorNode = valueNode.getRightChild().inOrderTraverse().get(0);
            AbstractTreeNode<E> rightChild = successorNode.getChildren().get(1);
            valueNode.setData(successorNode.getData());
            deleteNodeFromTree(successorNode);
            valueNode.children.set(1, rightChild);
        }
    }

    public boolean contains(E value) {
        return root.contains(value);
    }

    public List<E> findInRange(E minValue, E maxValue) {
        List<E> results = new ArrayList<>();
        List<BSTNode> nodes = getRoot().findInRange(minValue, maxValue);
        for (BSTNode node: nodes) {
            results.add(node.getData());
        }
        return results;
    }

    private void addNodeToTree(BSTNode node, BSTNode parentNode, int index) {
        if (parentNode == root) {
            parentNode.getChildren().set(0, node);
        } else {
            parentNode.getChildren().set(index, node);
        }
        node.setParent(parentNode);
    }

    private void deleteNodeFromTree(BSTNode node) {
        BSTNode parentNode = (BSTNode) node.getParent();
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

    private BSTNode getRoot() {
        return (BSTNode) root.getChildren().get(0);
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

        System.out.println(searchTree.findInRange(-1, -1));

    }

    class BSTNode extends AbstractTreeNode<E> {

        private final Comparator<E> orderMode;

        // We say that the left child is children[0] and the right child is children[1].

        public BSTNode(E data, Comparator<E> orderMode) {
            super(data, Arrays.asList(null, null));
            this.orderMode = orderMode;
        }

        public BSTNode(AbstractTreeNode<E> parent, E data, Comparator<E> orderMode) {
            super(parent, data, Arrays.asList(null, null));
            this.orderMode = orderMode;
        }

        public void addChild(BSTNode nodeToAdd) {
            int comparison = orderMode.compare(data, nodeToAdd.data);
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
        }

        public boolean contains(E value) {
            int comparison = orderMode.compare(data, value);
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

        public BSTNode findNode(E value) {
            int comparison = orderMode.compare(data, value);
            if (comparison == 0) {
                return this;
            } else if (comparison < 0 && hasRightChild()) {
                return getRightChild().findNode(value);
            } else if (comparison > 0 && hasLeftChild()) {
                return getLeftChild().findNode(value);
            }
            return null;
        }

        public List<BSTNode> findInRange(E minValue, E maxValue) {
            int minComp = orderMode.compare(data, minValue);
            int maxComp = orderMode.compare(data, maxValue);

            if (minComp >= 0 && 0 >= maxComp) {
                ArrayList<BSTNode> resultList = new ArrayList<>();
                resultList.add(this);
                if (hasLeftChild()) {
                    List<BSTNode> LList = getLeftChild().findInRange(minValue, maxValue);
                    resultList.addAll(LList);
                }
                if (hasRightChild()) {
                    List<BSTNode> RList = getRightChild().findInRange(minValue, maxValue);
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

        private void inOrderHelper(List<BSTNode> valList) {
            if (hasLeftChild()) getLeftChild().inOrderHelper(valList);
            valList.add(this);
            if (hasRightChild()) getRightChild().inOrderHelper(valList);
        }

        public List<BSTNode> inOrderTraverse() {
            List<BSTNode> result = new ArrayList<>();
            inOrderHelper(result);
            return result;
        }

        public BSTNode getLeftChild() {
            return (BSTNode) children.get(0);
        }

        public BSTNode getRightChild() {
            return (BSTNode) children.get(1);
        }

        public boolean hasLeftChild() {
            return children.get(0) != null;
        }

        public boolean hasRightChild() {
            return children.get(1) != null;
        }

        @Override
        protected E getData() {
            return super.getData();
        }

        @Override
        protected void setData(E data) {
            super.setData(data);
        }

        @Override
        protected int size() {
            int numNull = 0;
            for (AbstractTreeNode<E> child: children) {
                if (child == null) numNull++;
            }
            return 2 - numNull;
        }

        @Override
        protected boolean isRoot() {
            return super.isRoot();
        }

        @Override
        protected boolean isExternal() {
            return super.isExternal();
        }

        @Override
        protected boolean isInternal() {
            return !isExternal();
        }

        @Override
        protected AbstractTreeNode<E> getParent() {
            return super.getParent();
        }

    }

}
