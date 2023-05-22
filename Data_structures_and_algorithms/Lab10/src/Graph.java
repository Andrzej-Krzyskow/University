import java.util.*;

public class Graph {
    int[][] arr;
    HashMap<String, Integer> name2Int;
    ArrayList<Document> int2Name;

    public Graph(SortedMap<String, Document> internet) {

        int size = internet.size();
        arr = new int[size][size];
        int2Name = new ArrayList<>();
        name2Int = new HashMap<>();

        int i = 0;
        for (Document document : internet.values()) {
            int2Name.add(document);
            name2Int.put(document.name, i);
            i++;
        }

        i = 0;
        for (; i < size; i++) {

            Document document = int2Name.get(i);

            for (int j = 0; j < size; j++) {
                if (j == i) {
                    arr[i][j] = 0;
                    continue;
                }

                Link link = document.links.get(int2Name.get(j).name);
                if (link != null) {
                    arr[i][j] = name2Int.get(link.ref);
                } else {
                    arr[i][j] = -1;
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
        boolean[] visitedNodes = new boolean[int2Name.size()];

        queue.add(name2Int.get(start));
        visitedNodes[name2Int.get(start)] = true;

        while (!queue.isEmpty()) {

            int i = queue.remove();

            for (int j = 0; j < int2Name.size(); j++) {
                if (i != j && arr[i][j] != -1 && !visitedNodes[arr[i][j]]) {
                    queue.add(arr[i][j]);
                    visitedNodes[arr[i][j]] = true;
                    stringBuilder.append(int2Name.get(arr[i][j]).name).append(", ");
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
        boolean[] visitedNodes = new boolean[int2Name.size()];

        stack.push(name2Int.get(start));
        visitedNodes[name2Int.get(start)] = true;

        while (!stack.empty()) {

            int i = stack.pop();

            if (!visitedNodes[i]) {
                stringBuilder.append(int2Name.get(i).name).append(", ");
                visitedNodes[i] = true;
            }

            for (int j = int2Name.size()-1; j>=0; j--) {
                if (i != j && arr[i][j] != -1 && !visitedNodes[arr[i][j]]) {
                    stack.push(j);
                }

            }

        }

        stringBuilder.setLength(stringBuilder.length() - 2);
        return stringBuilder.toString();
    }

    public int connectedComponents() {

        DisjointSetForest disjointSetForest = new DisjointSetForest(int2Name.size());

        for (int i = 0; i < int2Name.size(); i++) {
            disjointSetForest.makeSet(i);
        }

        for (int i = 0; i < int2Name.size(); i++) {
            for (int j = 0; j < int2Name.size(); j++) {
                if (i != j && arr[i][j] != -1) {
                    disjointSetForest.union(i, j);
                }
            }
        }

        return disjointSetForest.countSets();
    }


}
