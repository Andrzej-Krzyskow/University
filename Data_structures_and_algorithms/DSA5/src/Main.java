import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("Task 1");
        int[] data1 = {54, 134, 12, 32, 94, 23, 12, 45, 96, 144, 123, 44, 3, 12, 33, 58, 90, 75, 1, 0};

        System.out.println("Before: " + Arrays.toString(data1));
        BucketSort.bucketSort(data1, 4, 150);
        System.out.println("After: " + Arrays.toString(data1));

        System.out.println("\nTask 2");
        int[] data2 = {54, 134, 12, 32, 94, 23, 12, 45, 96, 144, 123, 44, 3, 12, 33, 58, 90, 75, 1, 0};

        System.out.println("Before: " + Arrays.toString(data2));
        QuickSort.quickSort(data2);
        System.out.println("After: " + Arrays.toString(data2));

        System.out.println("\nTask 5");
        BinarySearchTree tree = createTree();


        System.out.print("Pre order: ");
        tree.preOrder();

        System.out.print("In order: ");
        tree.inorder();

        System.out.print("Post order: ");
        tree.postOrder();


        tree.delete(12);
        tree.delete(1);
        tree.delete(20);

        System.out.print("In order (after deletion): ");
        tree.inorder();

        System.out.println("\nTask 7");

        tree = createTree();

        System.out.println("Size: " + tree.getNodeNumber());
        System.out.println("Height: " + tree.getHeight());
        System.out.println("Even keys: " + tree.getEvenKeysNumber());
        System.out.println("Nodes with one child: " + tree.getNodesWithOneChild());
        System.out.println("Brothers number: " + tree.getBrothersNumber());
        System.out.println("Key with longest sequence of sub-notes with one child: " + tree.getKeyWithLongestSequence());


//                    20
//                  /    \
//                 7     25
//                /  \     \
//               4   10     30
//              /      \
//             1        12
//              \
//                2


        System.out.println("\nTask 8");
        System.out.println("Pre-order");

    }


    public static BinarySearchTree createTree() {
        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(20);
        tree.insert(7);
        tree.insert(10);
        tree.insert(25);
        tree.insert(4);
        tree.insert(1);
        tree.insert(2);
        tree.insert(12);
        tree.insert(30);

        return tree;

//                    20
//                  /    \
//                 7     25
//                /  \     \
//               4   10     30
//              /      \
//             1        12
//              \
//                2
    }
}
