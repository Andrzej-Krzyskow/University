import java.util.*;

public class Main {
    static Scanner scan; // for input stream

    public static void main(String[] args) {
        /*

        ListIterator<Integer> listIterator = twoWayUnorderedListWithHeadAndTail.innerListIterator();

        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        System.out.println("------------");
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
        System.out.println("------------");
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        listIterator.set(666);
        listIterator.previous();
        listIterator.set(999);
        listIterator.previous();
        listIterator.set(666);
        listIterator.previous();
        listIterator.set(666);
        listIterator.previous();
        listIterator.set(666);
        listIterator.previous();
        listIterator.set(666);
        System.out.println(twoWayUnorderedListWithHeadAndTail);*/
  /*      TwoWayUnorderedListWithHeadAndTail<Integer> twoWayUnorderedListWithHeadAndTail = new TwoWayUnorderedListWithHeadAndTail<>();
        TwoWayUnorderedListWithHeadAndTail<Integer> anotherList = new TwoWayUnorderedListWithHeadAndTail<>();

        twoWayUnorderedListWithHeadAndTail.add(1);
        twoWayUnorderedListWithHeadAndTail.add(2);
        twoWayUnorderedListWithHeadAndTail.add(3);
        twoWayUnorderedListWithHeadAndTail.add(1, 666 );
        System.out.println(twoWayUnorderedListWithHeadAndTail);
        System.out.println("-----------");

        //twoWayUnorderedListWithHeadAndTail.clear();

        anotherList.add(100);
        anotherList.add(200);
        anotherList.add(300);

        twoWayUnorderedListWithHeadAndTail.add(anotherList);
        System.out.println(twoWayUnorderedListWithHeadAndTail);
        System.out.println("---------");
        System.out.println(anotherList);

        ListIterator listIterator = twoWayUnorderedListWithHeadAndTail.innerListIterator();

        System.out.println(twoWayUnorderedListWithHeadAndTail.toStringReverse());
*/
/*        System.out.println(twoWayUnorderedListWithHeadAndTail);
        System.out.println("--------------------------------------");
        System.out.println("Size" + twoWayUnorderedListWithHeadAndTail.size());

        twoWayUnorderedListWithHeadAndTail.clear();
        twoWayUnorderedListWithHeadAndTail.add(0, 666);
        twoWayUnorderedListWithHeadAndTail.add(1);
        twoWayUnorderedListWithHeadAndTail.add(2);
        System.out.println(twoWayUnorderedListWithHeadAndTail);
        System.out.println("IND " + twoWayUnorderedListWithHeadAndTail.indexOf(666));


        System.out.println(twoWayUnorderedListWithHeadAndTail.isEmpty());
        System.out.println("CHANGED " + twoWayUnorderedListWithHeadAndTail.set(1, -100));
        System.out.println(twoWayUnorderedListWithHeadAndTail);


        System.out.println("REMOVED " + twoWayUnorderedListWithHeadAndTail.remove(1));
        twoWayUnorderedListWithHeadAndTail.add(123);
        twoWayUnorderedListWithHeadAndTail.add(321);
        System.out.println(twoWayUnorderedListWithHeadAndTail);


        System.out.println("REMOve: " + twoWayUnorderedListWithHeadAndTail.remove((Integer) 321));
        System.out.println(twoWayUnorderedListWithHeadAndTail);
        System.out.println(anotherList);


        //anotherList.clear();
        //anotherList = twoWayUnorderedListWithHeadAndTail;
        twoWayUnorderedListWithHeadAndTail.clear();
        twoWayUnorderedListWithHeadAndTail.add(123456);
        anotherList.add(123456);
        twoWayUnorderedListWithHeadAndTail.add(anotherList);

        System.out.println("AFTER ADD1  " + twoWayUnorderedListWithHeadAndTail);
        System.out.println("AFTER ADD2  " + anotherList);

        System.out.println("------------------");
        System.out.println(twoWayUnorderedListWithHeadAndTail);
        System.out.println("------------------");
        System.out.println(twoWayUnorderedListWithHeadAndTail.toStringReverse());*/

        System.out.println("START");
        scan = new Scanner(System.in);
        Document[] doc = null;
        int currentDocNo = 0;
        int maxNo = -1;
        boolean halt = false;
        while (!halt) {
            String line = scan.nextLine();
            // empty line and comment line - read next line
            if (line.length() == 0 || line.charAt(0) == '#')
                continue;
            // copy line to output (it is easier to find a place of a mistake)
            System.out.println("!" + line);
            String word[] = line.split(" ");
            // go n - start with array of the length n
            if (word[0].equalsIgnoreCase("go") && word.length == 2) {
                maxNo = Integer.parseInt(word[1]);
                doc = new Document[maxNo];
                continue;
            }
            //ch - change index
            if (word[0].equalsIgnoreCase("ch") && word.length == 2) {
                currentDocNo = Integer.parseInt(word[1]);
                continue;
            }

            // ld documentName
            if (word[0].equalsIgnoreCase("ld") && word.length == 2) {
                doc[currentDocNo] = new Document(word[1], scan);
                continue;
            }
            // ha
            if (word[0].equalsIgnoreCase("ha") && word.length == 1) {
                halt = true;
                continue;
            }
            // clear
            if (word[0].equalsIgnoreCase("clear") && word.length == 1) {
                doc[currentDocNo].link.clear();
                continue;
            }
            // show
            if (word[0].equalsIgnoreCase("show") && word.length == 1) {
                System.out.println(doc[currentDocNo].toString());
                continue;
            }
            // reverse
            if (word[0].equalsIgnoreCase("reverse") && word.length == 1) {
                System.out.println(doc[currentDocNo].toStringReverse());
                continue;
            }
            // size
            if (word[0].equalsIgnoreCase("size") && word.length == 1) {
                System.out.println(doc[currentDocNo].link.size());
                continue;
            }
            // add str
            if (word[0].equalsIgnoreCase("add") && word.length == 2) {
                System.out.println(doc[currentDocNo].link.add(new Link(word[1])));
                continue;
            }
            // addi index str
            if (word[0].equalsIgnoreCase("addi") && word.length == 3) {
                int index = Integer.parseInt(word[1]);
                try {
                    doc[currentDocNo].link.add(index, new Link(word[2]));
                } catch (NoSuchElementException e) {
                    System.out.println("error");
                }
                continue;
            }
            // get index
            if (word[0].equalsIgnoreCase("get") && word.length == 2) {
                int index = Integer.parseInt(word[1]);
                try {
                    Link l = doc[currentDocNo].link.get(index);
                    System.out.println(l.ref);
                } catch (NoSuchElementException e) {
                    System.out.println("error");
                }
                continue;
            }
            // set index str
            if (word[0].equalsIgnoreCase("set") && word.length == 3) {
                int index = Integer.parseInt(word[1]);
                try {
                    Link l = doc[currentDocNo].link.set(index, new Link(word[2]));
                    System.out.println(l.ref);
                } catch (NoSuchElementException e) {
                    System.out.println("error");
                }

                continue;
            }
            // index str
            if (word[0].equalsIgnoreCase("index") && word.length == 2) {
                int index = doc[currentDocNo].link.indexOf(new Link(word[1]));
                System.out.println(index);
                continue;
            }
            // remi index
            if (word[0].equalsIgnoreCase("remi") && word.length == 2) {
                int index = Integer.parseInt(word[1]);
                try {
                    Link l = doc[currentDocNo].link.remove(index);
                    System.out.println(l.ref);
                } catch (NoSuchElementException e) {
                    System.out.println("error");
                }
                continue;
            }
            // rem str
            if (word[0].equalsIgnoreCase("rem") && word.length == 2) {
                System.out.println(doc[currentDocNo].link.remove(new Link(word[1])));
                continue;
            }
            // addl <indexOfListArray>
            if (word[0].equalsIgnoreCase("addl") && word.length == 2) {
                int number = Integer.parseInt(word[1]);
                doc[currentDocNo].link.add(doc[number].link);
                continue;
            }

            // remdup
            if (word[0].equalsIgnoreCase("size") && word.length == 1) {
                doc[currentDocNo].link.removeDuplicates();
                continue;
            }
            System.out.println("Wrong command");
        }
        System.out.println("END OF EXECUTION");
        scan.close();

    }


}
