import java.util.HashSet;
import java.util.TreeSet;

public class DisjointSetLinkedList implements DisjointSetDataStructure {

	private static final int NULL=-1;
	Element[] arr;


	private static class Element{
		int representative;
		int next;
		int length;
		int last;
	}

	
	public DisjointSetLinkedList(int size) {
		arr = new Element[size];
	}
	
	@Override
	public void makeSet(int item) {
		Element e = new Element();

		e.representative = item;
		e.next = NULL;
		e.length = 1;
		e.last = item;
		arr[item] = e;
	}

	@Override
	public int findSet(int item) {

		return arr[item].representative;
	}

	@Override
	public boolean union(int itemA, int itemB) {
		int repA = findSet(itemA);
		int repB = findSet(itemB);

		if (repA == repB) {
			return false;
		}

		if (arr[repA].length < arr[repB].length) {
			int temp = repA;
			repA = repB;
			repB = temp;
		}

		arr[arr[repA].last].next = arr[repB].representative;

		int itemPointerOnB = arr[repB].representative;
		for (int i = 0; i < arr[repB].length; i++) {
			arr[itemPointerOnB].representative = repA;
			itemPointerOnB = arr[itemPointerOnB].next;
		}

		arr[repA].last = arr[repB].last;
		arr[repA].length += arr[repB].length;


		return true;
	}

	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("Disjoint sets as linked list:\n");
		HashSet<Integer> setOfRepresentatives = new HashSet<>();

		for (Element element:arr) {

			int rep = element.representative;

			if (!setOfRepresentatives.contains(rep)) {
				setOfRepresentatives.add(rep);

				int itemPointer = rep;
				while (itemPointer!=-1){
					stringBuilder.append(itemPointer).append(", ");
					itemPointer = arr[itemPointer].next;
				}
				stringBuilder.setLength(stringBuilder.length() - 2);
				stringBuilder.append("\n");
			}
		}


		stringBuilder.setLength(stringBuilder.length()-1);
		return stringBuilder.toString();
	}

}
