package DataStructures.Trees;

import java.util.Comparator;

public class AVLTest {

    public static void main(String[] args) {

        Integer[] data = {237457, -2348, 743, 34823, 237, 234-12, 0};

        Comparator<Integer> comparator = (o1, o2) -> {
            if (o1 < o2) {
                return -1;
            } else if (o1.equals(o2)) {
                return 0;
            } else {
                return 1;
            }
        };

        AVLTree<Integer> searchTree = new AVLTree<>(comparator);
        searchTree.add(data);

        System.out.println(searchTree);

        for (int d: data) {
            System.out.println("Deleting " + d + ":");
            searchTree.delete(d);
            System.out.println(searchTree);
        }

    }

}
