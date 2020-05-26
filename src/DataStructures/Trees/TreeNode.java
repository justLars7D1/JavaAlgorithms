package DataStructures.Trees;

import java.util.List;

public class TreeNode<E> extends AbstractTreeNode<E> {

    public TreeNode(E data) {
        super(data);
    }

    public TreeNode(AbstractTreeNode<E> parent, E data) {
        super(parent, data);
    }

    public TreeNode(AbstractTreeNode<E> parent, E data, List<AbstractTreeNode<E>> children) {
        super(parent, data, children);
    }



    @Override
    public E getData() {
        return super.getData();
    }

    @Override
    public void setData(E data) {
        super.setData(data);
    }

    @Override
    public int size() {
        return super.size();
    }

    @Override
    public boolean isRoot() {
        return super.isRoot();
    }

    @Override
    public boolean isExternal() {
        return super.isExternal();
    }

    @Override
    public boolean isInternal() {
        return super.isInternal();
    }

    @Override
    public AbstractTreeNode<E> getParent() {
        return super.getParent();
    }

    @Override
    public void setParent(AbstractTreeNode<E> parent) {
        super.setParent(parent);
    }

    @Override
    public List<AbstractTreeNode<E>> getChildren() {
        return super.getChildren();
    }

    @Override
    public void setChildren(List<AbstractTreeNode<E>> children) {
        super.setChildren(children);
    }
}
