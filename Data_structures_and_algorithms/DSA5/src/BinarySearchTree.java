public class BinarySearchTree {
    Node root;
    StringBuilder stringRepresentation = new StringBuilder();
    private int size = 0;
    private int height = 0;
    private int longestSequence;
    private int keyOfLongestSequence;
    private Node nodeWithKeyOfLongestSequence;
    boolean isSizeOk = false;
    boolean isHeightOk = false;

    private static class Node {
        int key;
        Node left, right;

        public Node(int item) {
            key = item;
            left = right = null;
        }

    }

    public BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(Node root) {
        this.root = root;
    }

    public void insert(int key) {
        root = insertRecursive(root, key);
        isSizeOk = false;
        isHeightOk = false;
    }

    private Node insertRecursive(Node root, int key) {

        if (root == null) {
            root = new Node(key);
            return root;
        }

        if (key < root.key) {
            root.left = insertRecursive(root.left, key);
        } else if (key > root.key) {
            root.right = insertRecursive(root.right, key);
        }

        return root;
    }

    private void print(Order order) {
        stringRepresentation.setLength(0);

        switch (order) {
            case IN_ORDER -> inorderRecursive(root);
            case PRE_ORDER -> preOrderRecursive(root);
            case POST_ORDER -> postOrderRecursive(root);

        }

        if (stringRepresentation.length() > 1) {
            stringRepresentation.setLength(stringRepresentation.length() - 2);
        } else {
            stringRepresentation.append("[]");
        }
        System.out.println(stringRepresentation.toString());
    }

    public void preOrder() {
        print(Order.PRE_ORDER);
    }

    public void inorder() {
        print(Order.IN_ORDER);
    }

    public void postOrder() {
        print(Order.POST_ORDER);
    }

    private void preOrderRecursive(Node root) {

        if (root != null) {
            stringRepresentation.append(root.key).append(", ");
            preOrderRecursive(root.left);
            preOrderRecursive(root.right);
        }

    }

    private void inorderRecursive(Node root) {

        if (root != null) {
            inorderRecursive(root.left);
            stringRepresentation.append(root.key).append(", ");
            inorderRecursive(root.right);
        }

    }

    private void postOrderRecursive(Node root) {

        if (root != null) {
            postOrderRecursive(root.left);
            postOrderRecursive(root.right);
            stringRepresentation.append(root.key).append(", ");
        }

    }

    public void delete(int key) {
        root = deleteRecursive(root, key);
    }

    private Node deleteRecursive(Node root, int key) {

        if (root == null) {
            return null;
        }

        if (key > root.key) {
            root.right = deleteRecursive(root.right, key);
        } else if (key < root.key) {
            root.left = deleteRecursive(root.left, key);
        } else {
            isSizeOk = false;
            isHeightOk = false;

            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.right = removeMinValue(root, root.right);
        }

        return root;
    }

    private Node removeMinValue(Node toRemove, Node node) {

        if (node.left != null) {
            node.left = removeMinValue(toRemove, node.left);
        } else {
            toRemove.key = node.key;
            node = node.right;
        }

        return node;
    }


    public int getNodeNumber() {

        if (!isSizeOk) {
            size = 0;
            size = recursiveGetNodeNumber(root);
            isSizeOk = true;
        }

        return size;
    }

    private int recursiveGetNodeNumber(Node root) {

        if (root == null) {
            return 0;
        }

        return recursiveGetNodeNumber(root.left) + recursiveGetNodeNumber(root.right) + 1;
    }

    public int getHeight() {
        if (!isHeightOk) {
            height = recursiveGetHeight(root, 0);
        }

        return height;
    }

    private int recursiveGetHeight(Node root, int currentSize) {

        if (root == null) {
            return currentSize;
        }

        return Math.max(recursiveGetHeight(root.left, currentSize + 1),
                recursiveGetHeight(root.right, currentSize + 1));

    }

    public int getEvenKeysNumber() {

        return recursiveGetEvenKeysNumber(root);
    }

    private int recursiveGetEvenKeysNumber(Node root) {

        if (root == null) {
            return 0;
        }

        if (root.key % 2 != 0) {
            return recursiveGetEvenKeysNumber(root.left) + recursiveGetEvenKeysNumber(root.right);
        }

        return recursiveGetEvenKeysNumber(root.left) + recursiveGetEvenKeysNumber(root.right) + 1;

    }

    public int getNodesWithOneChild() {

        return recursiveGetNodesWithOneChild(root);
    }

    private int recursiveGetNodesWithOneChild(Node root) {

        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right != null) {
            return recursiveGetNodesWithOneChild(root.right) + 1;
        }

        if (root.right == null && root.left != null) {
            return recursiveGetNodesWithOneChild(root.left) + 1;
        }

        return recursiveGetNodesWithOneChild(root.left) + recursiveGetNodesWithOneChild(root.right);
    }

    public int getBrothersNumber() {

        return recursiveGetBrothersNumber(root);
    }

    private int recursiveGetBrothersNumber(Node root) {

        if (root == null) {
            return 0;
        }

        if (root.left != null && root.right != null) {
            return recursiveGetBrothersNumber(root.left) + recursiveGetBrothersNumber(root.right) + 2;
        }

        return recursiveGetBrothersNumber(root.left) + recursiveGetBrothersNumber(root.right);

    }


    public int getKeyWithLongestSequence() {
        longestSequence = 0;
        nodeWithKeyOfLongestSequence = null;
        recursiveGetKeyWithLongestSequence(root);
        if (nodeWithKeyOfLongestSequence == null || (longestSequence == 1 && nodeWithKeyOfLongestSequence.right != null
                && nodeWithKeyOfLongestSequence.left != null)) {
            return -1;
        }
        return keyOfLongestSequence;
    }

    private int recursiveGetKeyWithLongestSequence(Node root) {

        if (root == null) {
            return 0;
        }

        if (root.right != null) {

            if (root.left != null) {
                if (recursiveGetKeyWithLongestSequence(root.right) + 1 > longestSequence) {
                    keyOfLongestSequence = root.key;
                    nodeWithKeyOfLongestSequence = root;
                }

                if (recursiveGetKeyWithLongestSequence(root.left) + 1 > longestSequence) {
                    keyOfLongestSequence = root.key;
                    nodeWithKeyOfLongestSequence = root;
                }

                return 0;
            }

            if (root.right.left == null) {
                int tmp = recursiveGetKeyWithLongestSequence(root.right.right) + 1;
                if (tmp > longestSequence) {
                    longestSequence = tmp;
                    keyOfLongestSequence = root.key;
                    nodeWithKeyOfLongestSequence = root;
                }
                return tmp;
            } else if (root.right.right == null) {
                int tmp = recursiveGetKeyWithLongestSequence(root.right.left) + 1;
                if (tmp > longestSequence) {
                    longestSequence = tmp;
                    keyOfLongestSequence = root.key;
                    nodeWithKeyOfLongestSequence = root;
                }
                return tmp;
            }
        }

        if (root.left != null) {
            if (root.left.left == null) {
                int tmp = recursiveGetKeyWithLongestSequence(root.left.right) + 1;
                if (tmp > longestSequence) {
                    longestSequence = tmp;
                    keyOfLongestSequence = root.key;
                    nodeWithKeyOfLongestSequence = root;
                }
                return tmp;
            } else if (root.left.right == null) {
                int tmp = recursiveGetKeyWithLongestSequence(root.left.left) + 1;
                if (tmp > longestSequence) {
                    longestSequence = tmp;
                    keyOfLongestSequence = root.key;
                    nodeWithKeyOfLongestSequence = root;
                }
                return tmp;
            }
        }

        return 1;
    }
}
