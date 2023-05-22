public class BST<T> {

    private int size;
    private Node root = null;

    private class Node {
        T value;
        Node left, right, parent;

        public Node(T v) {
            value = v;
        }

        public Node(T value, Node left, Node right, Node parent) {
            super();
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }
    }

    private enum Order {
        IN_ORDER,
        PRE_ORDER,
        POST_ORDER
    }

    public BST() {
        size = 0;
    }

    public T getElement(T toFind) {

        Node node = getNode(toFind);

        if (node != null) {
            return node.value;
        }

        return null;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private Node getNode(T toFind) {

        Node node = root;

        while (node != null) {

            if (((Comparable) toFind).compareTo(node.value) > 0) {
                node = node.right;
            } else if (((Comparable) toFind).compareTo(node.value) < 0) {
                node = node.left;
            } else {
                return node;
            }
        }

        return null;

    }

    public T successor(T elem) {
        Node node = getSuccessorNode(elem);

        if (node != null) {
            return node.value;
        }

        return null;
    }

    private Node getSuccessorNode(T elem) {
        Node node = getNode(elem);

        if (node == null) {
            return null;
        }

        if (node.right != null) {
            return getMinimum(node.right);
        }

        Node parent = node.parent;

        while (parent != null && node == parent.right) {
            node = parent;
            parent = parent.parent;
        }

        return parent;
    }

    private Node getMinimum(Node root) {

        while (root.left != null) {
            root = root.left;
        }

        return root;
    }

    public String toStringInOrder() {

        return getStringOrder(Order.IN_ORDER);
    }

    public String toStringPreOrder() {

        return getStringOrder(Order.PRE_ORDER);
    }

    public String toStringPostOrder() {

        return getStringOrder(Order.POST_ORDER);
    }

    private String getStringOrder(Order order) {

        if (root == null) {
            return "";
        }

        StringBuilder stringBuilder = new StringBuilder();
        switch (order) {
            case IN_ORDER: {
                recursiveInOrder(root, stringBuilder);
                break;
            }

            case PRE_ORDER: {
                recursivePreOrder(root, stringBuilder);
                break;
            }

            case POST_ORDER: {
                recursivePostOrder(root, stringBuilder);
                break;
            }

        }

        stringBuilder.setLength(stringBuilder.length() - 2);

        return stringBuilder.toString();
    }

    private void recursiveInOrder(Node node, StringBuilder stringBuilder) {
        if (node == null) {
            return;
        }

        recursiveInOrder(node.left, stringBuilder);
        stringBuilder.append(node.value).append(", ");
        recursiveInOrder(node.right, stringBuilder);
    }

    private void recursivePreOrder(Node node, StringBuilder stringBuilder) {
        if (node == null) {
            return;
        }

        stringBuilder.append(node.value).append(", ");
        recursivePreOrder(node.left, stringBuilder);
        recursivePreOrder(node.right, stringBuilder);
    }

    private void recursivePostOrder(Node node, StringBuilder stringBuilder) {
        if (node == null) {
            return;
        }

        recursivePostOrder(node.left, stringBuilder);
        recursivePostOrder(node.right, stringBuilder);
        stringBuilder.append(node.value).append(", ");
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public boolean add(T elem) {

        Node parent = null;
        Node node = root;

        while (node != null) {
            parent = node;

            if (((Comparable) elem).compareTo(node.value) > 0) {
                node = node.right;
            } else if (((Comparable) elem).compareTo(node.value) < 0) {
                node = node.left;
            } else {
                return false;
            }
        }

        if (parent == null) {
            root = new Node(elem);
        } else if (((Comparable) elem).compareTo(parent.value) > 0) {
            parent.right = new Node(elem);
            parent.right.parent = parent;
        } else {
            parent.left = new Node(elem);
            parent.left.parent = parent;
        }

        size++;
        return true;
    }

    public T remove(T value) {

        Node toRemove = getNode(value);
        Node reallyRemoved;
        Node childToConnect;

        if (toRemove == null) {
            return null;
        }


        if (toRemove.left != null && toRemove.right != null) {
            reallyRemoved = this.getSuccessorNode(toRemove.value);
        } else {
            reallyRemoved = toRemove;
        }

        assert reallyRemoved != null;
        if (reallyRemoved.left != null) {
            childToConnect = reallyRemoved.left;
        } else {
            childToConnect = reallyRemoved.right;
        }

        if (childToConnect != null) {
            childToConnect.parent = reallyRemoved.parent;
        }

        if (reallyRemoved.parent == null) {
            root = childToConnect;
        } else if (reallyRemoved == reallyRemoved.parent.left) {
            reallyRemoved.parent.left = childToConnect;
        } else {
            reallyRemoved.parent.right = childToConnect;
        }

        if (reallyRemoved != toRemove) {
            T temp = toRemove.value;
            toRemove.value = reallyRemoved.value;
            reallyRemoved.value = temp;
        }

        size--;
        return reallyRemoved.value;
    }

    public void clear() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

}
