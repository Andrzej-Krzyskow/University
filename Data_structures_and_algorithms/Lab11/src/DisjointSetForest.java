import java.util.HashSet;

public class DisjointSetForest implements DisjointSetDataStructure {

	Element[] arr;

	private static class Element {
		int rank;
		int parent;
	}


	public DisjointSetForest(int size) {
		arr = new Element[size];
	}

	@Override
	public void makeSet(int item) {
		Element e = new Element();
		e.rank = 0;
		e.parent = item;

		arr[item] = e;
	}

	@Override
	public int findSet(int item) {

		if (arr[item].parent == item) {
			return item;
		}

		return arr[item].parent = findSet(arr[item].parent);
	}

	@Override
	public boolean union(int itemA, int itemB) {
		int repA = findSet(itemA);
		int repB = findSet(itemB);

		if (repA == repB) {
			return false;
		}

		if (arr[repA].rank > arr[repB].rank) {
			int temp = repA;
			repA = repB;
			repB = temp;
		}

		arr[repA].parent = repB;


		if (arr[repA].rank == arr[repB].rank) {
			arr[repB].rank++;
		}

		return true;
	}

	@Override
	public int countSets() {

		HashSet<Integer> hashSet = new HashSet<>();

		for (Element element : arr) {
			hashSet.add(element.parent);
		}

		return hashSet.size();
	}


	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("Disjoint sets as forest:\n");

		for (int i = 0; i < arr.length; i++) {
			stringBuilder.append(i).append(" -> ").append(arr[i].parent).append("\n");
		}

		stringBuilder.setLength(stringBuilder.length()-1);
		return stringBuilder.toString();
	}
}
