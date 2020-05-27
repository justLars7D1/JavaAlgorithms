package DataStructures.Trees;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BSTNode<E> extends AbstractTreeNode<E> {

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

    public void addChild(BSTNode<E> nodeToAdd) {
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

    public BSTNode<E> findNode(E value) {
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

    private void inOrderHelper(List<BSTNode<E>> valList) {
        if (hasLeftChild()) getLeftChild().inOrderHelper(valList);
        valList.add(this);
        if (hasRightChild()) getRightChild().inOrderHelper(valList);
    }

    public List<BSTNode<E>> inOrderTraverse() {
        List<BSTNode<E>> result = new ArrayList<>();
        inOrderHelper(result);
        return result;
    }

    public BSTNode<E> getLeftChild() {
        return (BSTNode<E>) children.get(0);
    }

    public BSTNode<E> getRightChild() {
        return (BSTNode<E>) children.get(1);
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
        return super.isInternal();
    }

    @Override
    protected AbstractTreeNode<E> getParent() {
        return super.getParent();
    }

}
