package DataStructures.Trees;

import java.util.Comparator;

public class AVLTest {

    public static void main(String[] args) {

        // There is still a bug when doing an in-order pass to swap elements when removing one from the tree
        String[] data = {"Lars", "ThanksJo!", "Testing", "BST", "AVL", "Tree", "Bla", "WowNiceTree"};

        Comparator<String> comparator = (o1, o2) -> {
            if (o1.compareTo(o2) < 0) {
                return -1;
            } else if (o1.compareTo(o2) == 0) {
                return 0;
            } else {
                return 1;
            }
        };

        AVLTree<String> searchTree = new AVLTree<>(comparator);
        searchTree.add(data);

        System.out.println(searchTree);

        for (String d: data) {
            System.out.println("Deleting " + d + ":");
            searchTree.delete(d);
            System.out.println(searchTree);
        }

    }

}
