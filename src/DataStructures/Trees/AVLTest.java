package DataStructures.Trees;

import java.util.Comparator;

public class AVLTest {

    public static void main(String[] args) {

        // There is still a bug when doing an in-order pass to swap elements when removing one from the tree
        Integer[] data = {1,2,3,4,5,6,7,6,5,4,3,2,1};

        Comparator<Integer> comparator = (o1, o2) -> {
            if (o1.compareTo(o2) < 0) {
                // Here, o1 is smaller than o2
                return -1;
            } else if (o1.compareTo(o2) == 0) {
                // Here, o1 equals o2
                return 0;
            } else {
                // Here, o1 is bigger than o2
                return 1;
            }
        };

        AVLTree<Integer> searchTree = new AVLTree<>(comparator);
        searchTree.add(data);

        System.out.println(searchTree);

        System.out.println(searchTree.contains(Integer.MAX_VALUE));

        System.out.println(searchTree.inOrderTraverse());

    }

}
