import java.util.*;

public class Graph {
    int[][] arr;
    HashMap<String, Integer> name2Int;
    ArrayList<Document> int2Doc;

    public Graph(SortedMap<String, Document> internet) {

        int size = internet.size();
        arr = new int[size][size];
        int2Doc = new ArrayList<>();
        name2Int = new HashMap<>();

        int i = 0;
        for (Document document : internet.values()) {
            int2Doc.add(document);
            name2Int.put(document.name, i);
            i++;
        }

        i = 0;
        for (; i < size; i++) {

            Document document = int2Doc.get(i);

            for (int j = 0; j < size; j++) {
                if (j == i) {
                    arr[i][j] = 0;
                    continue;
                }

                Link link = document.links.get(int2Doc.get(j).name);
                if (link != null) {
                    arr[i][j] = link.weight;
                } else {
                    arr[i][j] = Integer.MAX_VALUE;
                }

            }

        }

    }

    public String bfs(String start) {

        if (name2Int.get(start) == null) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder(start).append(", ");
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visitedNodes = new boolean[int2Doc.size()];

        queue.add(name2Int.get(start));
        visitedNodes[name2Int.get(start)] = true;

        while (!queue.isEmpty()) {

            int i = queue.remove();

            for (int j = 0; j < int2Doc.size(); j++) {
                if (i != j && arr[i][j] != Integer.MAX_VALUE && !visitedNodes[j]) {
                    queue.add(j);
                    visitedNodes[j] = true;
                    stringBuilder.append(int2Doc.get(j).name).append(", ");
                }
            }

        }


        stringBuilder.setLength(stringBuilder.length() - 2);
        return stringBuilder.toString();
    }

    public String dfs(String start) {

        if (name2Int.get(start) == null) {
            return null;
        }

        StringBuilder stringBuilder = new StringBuilder(start).append(", ");
        Stack<Integer> stack = new Stack<>();
        boolean[] visitedNodes = new boolean[int2Doc.size()];

        stack.push(name2Int.get(start));
        visitedNodes[name2Int.get(start)] = true;

        while (!stack.empty()) {

            int i = stack.pop();

            if (!visitedNodes[i]) {
                stringBuilder.append(int2Doc.get(i).name).append(", ");
                visitedNodes[i] = true;
            }

            for (int j = int2Doc.size() - 1; j >= 0; j--) {
                if (i != j && arr[i][j] != Integer.MAX_VALUE && !visitedNodes[j]) {
                    stack.push(j);
                }

            }

        }

        stringBuilder.setLength(stringBuilder.length() - 2);
        return stringBuilder.toString();
    }

    public int connectedComponents() {

        DisjointSetForest disjointSetForest = new DisjointSetForest(int2Doc.size());

        for (int i = 0; i < int2Doc.size(); i++) {
            disjointSetForest.makeSet(i);
        }

        for (int i = 0; i < int2Doc.size(); i++) {
            for (int j = 0; j < int2Doc.size(); j++) {
                if (i != j && arr[i][j] != Integer.MAX_VALUE) {
                    disjointSetForest.union(i, j);
                }
            }
        }

        return disjointSetForest.countSets();
    }

    public String DijkstraSSSP(String startVertexStr) {

        if (name2Int.get(startVertexStr) == null) {
            return null;
        }

        PriorityQueue<Node> queue = new PriorityQueue<>();
        StringBuilder out = new StringBuilder();
        Node[] nodes = new Node[name2Int.size()];

        for (int j = 0; j < name2Int.size(); j++) {
            nodes[j] = new Node(j);
        }

        nodes[name2Int.get(startVertexStr)].predecessor = name2Int.get(startVertexStr);
        nodes[name2Int.get(startVertexStr)].distance = 0;
        queue.add(nodes[name2Int.get(startVertexStr)]);

        while (!queue.isEmpty()) {

            Node minimum = queue.remove();

            for (int j = 0; j < name2Int.size(); j++) {
                Node checkedNode = nodes[j];

                if (arr[minimum.document][j] != Integer.MAX_VALUE && minimum.distance + arr[minimum.document][j] < checkedNode.distance) {
                    checkedNode.distance = minimum.distance + arr[minimum.document][j];
                    checkedNode.predecessor = minimum.document;
                    queue.add(checkedNode);
                }
            }
        }

        for (int j = 0; j < name2Int.size(); j++) {
            out.append(getPath(nodes,nodes[j])).append("\n");
        }

        return out.toString();
    }

    private String getPath(Node[] nodes, Node node) {
        StringBuilder path = new StringBuilder();

        if (node.predecessor == -1) {
            return "no path to " + int2Doc.get(node.document).name;
        }

        path.append(node.distance).append("=");

        while (node.predecessor != node.document) {
            path.append(int2Doc.get(node.document).name).append(">-");
            node = nodes[node.predecessor];
        }

        path.append(int2Doc.get(node.document).name);

        return path.reverse().toString();
    }

    private static class Node implements Comparable<Node> {

        int distance;
        int predecessor;
        int document;

        public Node(int document) {
            this.predecessor = -1;
            this.distance = Integer.MAX_VALUE;
            this.document = document;
        }

        @Override
        public String toString() {
            return "dist=" + distance +
                    " pred=" + predecessor;
        }

        @Override
        public int compareTo(Node o) {

            return Integer.compare(this.distance, o.distance);
        }
    }

}
