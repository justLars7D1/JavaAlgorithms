package DataStructures.Trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractTreeNode<E> {

    protected E data;

    protected AbstractTreeNode<E> parent;
    protected List<AbstractTreeNode<E>> children;

    public AbstractTreeNode(E data) {
        this.data = data;
    }

    public AbstractTreeNode(AbstractTreeNode<E> parent, E data) {
        this.parent = parent;
        this.data = data;
    }

    public AbstractTreeNode(AbstractTreeNode<E> parent, E data, List<AbstractTreeNode<E>> children) {
        this.parent = parent;
        this.data = data;
        this.children = children;
    }

    public AbstractTreeNode(E data, List<AbstractTreeNode<E>> children) {
        this.data = data;
        this.children = children;
    }

    protected E getData() {
        return data;
    }

    protected void setData(E data) {
        this.data = data;
    }

    protected int size() {
        return children.size();
    }

    protected boolean isRoot() {
        return parent == null;
    }

    protected boolean isExternal() {
        return size() == 0;
    }

    protected boolean isInternal() {
        return !isExternal();
    }

    protected AbstractTreeNode<E> getParent() {
        return parent;
    }

    protected void setParent(AbstractTreeNode<E> parent) {
        this.parent = parent;
    }

    protected List<AbstractTreeNode<E>> getChildren() {
        return children;
    }

    protected void setChildren(List<AbstractTreeNode<E>> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Root: " + toString(1);
    }

    protected String toString(int depth) {
        StringBuilder strData = new StringBuilder("" + data.toString() + "\n");
        for (int j = 0; j < children.size(); j++) {
            AbstractTreeNode<E> child = children.get(j);
            if (child != null) {
                for (int i = 0; i < depth; i++) {
                    strData.append("\t");
                }
                strData.append("Child ").append(j).append(": ").append(child.toString(depth+1));
            }
        }
        return strData.toString();
    }

    public List<E> preOrderTraverse() {
        List<E> result = new ArrayList<>();
        preOrderHelper(result);
        return result;
    }

    public List<E> postOrderTraverse() {
        List<E> result = new ArrayList<>();
        postOrderHelper(result);
        return result;
    }

    private void preOrderHelper(List<E> valList) {
        valList.add(data);
        for (AbstractTreeNode<E> child: children) {
            if (child == null) continue;
            child.preOrderHelper(valList);
        }
    }

    private void postOrderHelper(List<E> valList) {
        for (AbstractTreeNode<E> child: children) {
            if (child == null) continue;
            child.postOrderHelper(valList);
        }
        valList.add(data);
    }

}
